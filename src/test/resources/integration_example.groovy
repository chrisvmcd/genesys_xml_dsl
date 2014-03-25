configuration {
    reference {
        tenant "id": "Resources", "name": "Resources"
        folder "id": "AFolder", "DBID": 1234
    }
    create {
        application name: "WD1IV12B_CUSTOMER_SEARCH", {
            options {
                list_pair {
                    str_pair key: "logging.level", value: "DEBUG"
                    str_pair key: "clws.url", value: "https://test.com"
                }
            }
        }
    }
}