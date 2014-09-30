class UrlMappings {

	static mappings = {
        "/match_requests"(resources: 'matchRequest')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
