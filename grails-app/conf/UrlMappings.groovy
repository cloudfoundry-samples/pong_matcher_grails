class UrlMappings {

	static mappings = {
        "/all"(controller: "everyResource") {
            action = [DELETE: "delete"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
