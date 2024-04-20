//
//  inputView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 5/12/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct inputView: View {
    @Binding var text: String
    let title: String
    let placeHolder: String
    var isSecureField = false
    
    var body: some View {
        VStack(alignment: .leading,spacing: 12){
            
            Text(title)
                .fontWeight(.semibold)
                .foregroundColor(Color(red: 0.157, green: 0.1568627450980392, blue: 0.1568627450980392))
                .font(.footnote)
            
            if isSecureField{
                SecureField(placeHolder, text: $text)
                    .font(.system(size: 14))
            }
            else{
                TextField(placeHolder, text: $text)
                    .font(.system(size: 14))
            }
            Divider()
        }
            
    }
}

struct inputView_Previews: PreviewProvider {
    static var previews: some View {
        inputView(text: .constant(""), title: "Username", placeHolder: "Username")
    }
}
