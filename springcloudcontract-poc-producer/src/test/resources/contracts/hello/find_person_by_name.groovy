package hello

org.springframework.cloud.contract.spec.Contract.make {
    
        // Ignore a Contract
//         ignored()

        description "should return person by id=1"

        request {
                url "/person/daniel/adams"
                method GET()
        }

        response {
                status OK()
                headers {
                        contentType applicationJson()
                }
                body (
                        id: 1,
                        firstName: "Daniel",
                        lastName: "Adams"
                        
//                         Use Regex!
//                        "client.id": $(regex('[0-9]{10}'))
//                        "client.id": $(regex(email))
//                        "client.id": $(regex(uuid))
                        
                        //Dynamic Properties
                        //value(consumer(...), producer(...))
                )
        }
}