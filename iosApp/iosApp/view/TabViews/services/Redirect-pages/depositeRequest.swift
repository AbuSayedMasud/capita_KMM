//
//  depositeRequest.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 27/5/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct depositeRequest: View {
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
        
            .navigationBarTitle("Deposit Status", displayMode: .inline)
            .navigationBarItems(
                leading: Button(action: {
                    // Action for the first button
                }) {
                    Image("loading_nav_button")
                },
                trailing: HStack {
                    Button(action: {
                        // Action for the second button
                        // Show your alert or perform an action
                    }) {
                        Image("search_nav_button")
                    }
                    Button(action: {
                        // Action for another button
                    }) {
                        Image("alarm_nav_button")
                    }
                }
            )
    }
}

#Preview {
    depositeRequest()
}
