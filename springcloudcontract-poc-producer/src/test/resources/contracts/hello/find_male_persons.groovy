package contracts.hello

org.springframework.cloud.contract.spec.Contract.make {
    
        // Ignore a Contract
//         ignored()

        description "should return person by id=1"

        request {
                url "/persons/male"
                method GET()
        }

        response {
                status OK()
                headers {
                        contentType applicationJson()
                }
                body (
                        file("genderresponse.json")
                )
        }
}