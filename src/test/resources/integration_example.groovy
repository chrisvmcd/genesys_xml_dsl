configuration {
    reference {
        tenant "id":"Resources", "name":"Resources"
        folder "id":"AFolder", "DBID":1234
    }
    create {
        application name: "WD1IV12B_CUSTOMER_SEARCH", {
            options "logging.level": "DEBUG",
                    "clws.url": "https://test.com"
        }
    }
}