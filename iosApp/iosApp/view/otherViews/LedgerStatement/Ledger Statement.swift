//
//  Ledger Statement.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 8/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct Ledger_Statement: View {
    
    @State private var selectedTabIndex = 0
    let names = ["All", "Debit", "Credit"]
    
    
    var body: some View {
        VStack {
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(0..<names.count) { index in
                        Button(action: {
                            self.selectedTabIndex = index
                        }) {
                            VStack {
                                Text(self.names[index])
                                    .padding()
                                    .foregroundColor(self.selectedTabIndex == index ? .white : Color.white.opacity(0.5))
                                if self.selectedTabIndex == index {
                                    Rectangle()
                                        .frame(width: UIScreen.main.bounds.width / 3, height: 3)
                                        .foregroundColor(.white)
                                }
                            }
                            .frame(width: UIScreen.main.bounds.width/3, height: 68)
                        }
                    }
                }
                .background(appColor)
            }
            
            // Placeholder for content
            switch selectedTabIndex {
            case 0:
                all_statements()
            case 1:
                debit()
            case 2:
                credit()
            default:
                Text("No Content")
            }
            
            Spacer() // Add space to push buttons to the bottom
        }
        .navigationBarTitle(Text("Protfolio Statement"), displayMode: .inline)
        .navigationBarItems(
            trailing:
            HStack(spacing: 16) { // Stack for buttons on the right
                Button(action: {
                    // Action for first button
                }) {
                    Image(uiImage: UIImage(named: "loading_nav_button")!)
                }
                Button(action: {
                    // Action for second button
                }) {
                    Image(uiImage: UIImage(named: "alarm_nav_button")!)
                }
            }
        )
    }

}

#Preview {
    Ledger_Statement()
}
