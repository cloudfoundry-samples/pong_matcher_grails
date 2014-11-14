class UrlMappings {

	static mappings = {
        "/all"(controller: "everyResource", action: "delete", method: "DELETE")

        "/match_requests/$id"(controller: "matchRequest") {
            action = [GET: "show", PUT: "save"]
        }

        "/results"(controller: "result", action: "save", method: "POST")
        "/matches/$id"(controller: "match", action: "show", method: "GET")
        "/"(view: "/index")
        "500"(view: '/error')
	}
}
