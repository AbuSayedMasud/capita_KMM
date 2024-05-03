//
//  loginModel.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 2/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

// Struct to represent different authentication results
enum AuthResult {
    case success
    case invalid(errorMessage: [String])
}

// Class for handling login functionality
class LoginManager {
    func login(username: String, password: String, completion: @escaping (AuthResult) -> Void) {
        
        // Create an instance of SecurityFactory
        let securityFactory = SecurityFactory()
        
        // Call the getIdentityService method on the instance
        let identityService = securityFactory.getIdentityService(name: "LEADS")
        
        // Check if identityService is nil
        if identityService == nil {
            // Handle the case where identityService is nil
            completion(.invalid(errorMessage: ["Unable to retrieve identity service"]))
            return
        }
        
        // Now, you can use identityService to authenticate the user
        //let identityLoginResponse = identityService.authenticate(username: username, password: password)
        let identityLoginResponse: String = identityService.authenticate(username: username, password: password)
        
        print(identityLoginResponse)
        
        // Assuming Json.parseToJsonElement(_:).jsonObject returns a Swift Dictionary
        guard let jsonObject = try? JSONSerialization.jsonObject(with: Data(identityLoginResponse.utf8), options: []) as? [String: Any] else {
            // Handle the case where JSON parsing fails
            return
        }
        print(jsonObject)
        
        // Extract the token from the JSON object
        if let token = jsonObject["token"] as? String, !token.isEmpty {
            // Token is not empty, indicating successful authentication
            // Proceed with success handling
            completion(.success)
        } else {
            
            if let errorDetails = jsonObject["details"] as? [String], !errorDetails.isEmpty {
                           // Use error details in the error message
                           completion(.invalid(errorMessage: errorDetails))
                       } else {
                           // If error details are not available, provide a generic error message
                           completion(.invalid(errorMessage: ["Authentication failed"]))
                       }
            
        }
    }
}
