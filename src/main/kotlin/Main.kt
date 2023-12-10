import java.util.Scanner

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    var archives: MutableList<Archive> = mutableListOf()

    fun addNotify(archive: Archive) {
        println("Введите имя заметки:")
        val nameNotify = scan.nextLine()
        if (nameNotify == "") {
            println("Имя заметки не может быть пустым")
            return
        }

        println("Введите текст заметки:")
        val textNotify = scan.nextLine()
        if (textNotify == "") {
            println("Текст заметки не может быть пустым")
            return
        }

        val notify = Note(nameNotify, textNotify)
        archive.notes.add(notify)

        println("Заметка \"${notify.name}\" успешно добавлена")
    }

    fun showNote(archive: Archive, idNote: String) {
        if (!checkScanValue(idNote)) {
            println("Вы должны ввести цифру")
            return
        }

        val note = archive.notes.getOrNull(idNote.toInt() - 1)
        if (note == null) {
            println("Индекса заметки не существует")
            return
        }

        println("${note.name}: ${note.text}")
    }

    fun showNotes(archive: Archive) {
        println("Список заметок:")
        for (i in 0 until archive.notes.size) {
            println("${i + 1}. ${archive.notes[i].name}")
        }
        println("${archive.notes.size + 1}. Назад")
        println("Для просмотра заметки введите ID заметки:")
        println("")

        val idNote = scan.nextLine()

        when(idNote) {
            (archive.notes.size + 1).toString() -> return
            else -> showNote(archive, idNote)
        }
    }

    fun showArchive(archive: Archive) {
        println("Просмотр архива \"${archive.name}\"")
        while(true) {
            println("1. добавление заметки")
            println("2. просмотр заметок")
            println("3. назад")
            println("4. выход")

            when(scan.nextLine()) {
                "1" -> addNotify(archive)
                "2" -> showNotes(archive)
                "3" -> return
                "4" -> System.exit(0)
            }
        }
    }

    fun openArchive(archiveId: String) {
        if (!checkScanValue(archiveId)) {
            println("Вы должны ввести цифру")
            return
        }

        val archiveFind = archives.getOrNull(archiveId.toInt() - 1)
        if (archiveFind == null) {
            println("Индекс не верен")
        } else {
            showArchive(archiveFind)
        }
    }

    fun createArchive() {
        println("Введите имя архива: ")
        val name = scan.nextLine()
        if (name == "") {
            println("Имя архива не может быть пустым")
        } else {
            val archive = Archive(name, mutableListOf())
            archives.add(archive)
            println("Архив \"${archive.name}\" успешно создан")
        }
    }

    fun listArchive() {
        println("Список архивов:")
        for (i in 0 until archives.size) {
            println("${i + 1}. ${archives[i].name}")
        }

        println("${archives.size + 1}. Назад")
        println("${archives.size + 2}. Выход")
        println("Для просмотра архива введите его ID:")

        var archiveAction = scan.nextLine()

        when(archiveAction) {
            (archives.size + 1).toString() -> return
            (archives.size + 2).toString() -> System.exit(0)
            else -> openArchive(archiveAction)
        }
        println("")
    }

    fun start() {
        while(true) {
            println("Главное меню: ")
            println("1. Создать архив")
            println("2. Список архивов")
            println("3. Выход")

            val numberMenuStr = scan.nextLine()

            if (!checkScanValue(numberMenuStr)) {
                println("Вы должны ввести цифру")
                continue
            }

            when(numberMenuStr.toInt()) {
                1 -> createArchive()
                2 -> listArchive()
                3 -> return
                else -> println("Такой цифры нет в меню, введите корректное число")
            }
        }
    }

    start()
}