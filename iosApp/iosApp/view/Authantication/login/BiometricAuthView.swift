//
//  BiometricAuthView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 6/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import LocalAuthentication

struct BiometricAuthView: View {
    var onSuccess: () -> Void // Closure to handle success
    
    func authenticateUser() {
        let context = LAContext()
        var error: NSError?

        if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
            let reason = "Authenticate to proceed"

            context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: reason) { success, authenticationError in
                DispatchQueue.main.async {
                    if success {
                        // Biometric authentication successful
                        onSuccess() // Call the success closure
                    } else {
                        // Authentication failed
                        if let error = authenticationError {
                            print("Authentication failed: \(error.localizedDescription)")
                        }
                        // Handle authentication failure
                    }
                }
            }
        } else {
            // Biometric authentication not available or failed
            if let error = error {
                print("Biometric authentication unavailable: \(error.localizedDescription)")
            }
            // Handle no biometric authentication available
        }
    }
    
    var body: some View {
        VStack {
            // You can design your biometric authentication UI here if needed
        }
        .onAppear {
            // Call the authentication function when this view appears
            authenticateUser()
        }
    }
}

