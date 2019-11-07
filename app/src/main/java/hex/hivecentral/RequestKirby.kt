package hex.hivecentral

    import feign.Param
    import feign.RequestLine

    interface RequestKirby {

        @RequestLine("GET /zxz9w")
        fun getNote(): NotesAdapter.Note?
    }