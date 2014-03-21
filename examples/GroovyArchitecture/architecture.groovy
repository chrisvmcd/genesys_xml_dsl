architecture {
	jar "spring.jar"
	
	rules {
		"beans-web" {
			comment = "org.springframework.beans.factory cannot depend on org.springframework.web"
			
			'package' "org.springframework.beans"
			
			violation "org.springframework.web"
		}
		

	}
}