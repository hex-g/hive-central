package hex.hivecentral

    import feign.Param
    import feign.RequestLine

    interface RequestKirby {

        @RequestLine("GET /note/{id}")
        fun getNote(@Param("id") id: Int): NotesAdapter.Note?
    }