package hex.hivecentral

    import feign.RequestLine

    interface RequestKirby {

        @RequestLine("GET /zxz9w")
        fun getNote(): NotesAdapter.Note?

        @RequestLine("GET /tree")
        fun getTree(): NoteNode?
    }