//
//  StocksView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 11/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct StocksRow: View {
    var name: String
    var title: String
    var value: String
    var value2: String
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text(name)
                    .fontWeight(.bold)
                Spacer()
                Text(value)
                    .fontWeight(.bold)
            }
            HStack {
                Text(title)
                Spacer()
                Text(value2)
            }
        }
        .padding()
        //.background(Color.white)
        .background(Color(UIColor(named: "sectionTheme")!))
        .cornerRadius(10)
        .shadow(radius: 5)
        .padding(.horizontal)
    }
}

struct StocksView: View {
    let stocks = [
        ("Stock1", "Title1", "Value1", "Value2_1"),
        ("Stock2", "Title2", "Value2", "Value2_2"),
        ("Stock3", "Title3", "Value3", "Value2_3")
    ]
    
    var body: some View {
        ZStack(alignment: .bottomTrailing) {
            ScrollView {
                VStack(spacing: 16) {
                    ForEach(stocks, id: \.self.0) { stock in
                        StocksRow(name: stock.0, title: stock.1, value: stock.2, value2: stock.3)
                    }
                }
                .padding(.vertical)
            }
            // Button at the bottom right corner
            Button(action: {
                // Action for the button
            }) {
                Image(uiImage: UIImage(named: "filter")!)
                    .resizable()
                    .frame(width: 80, height: 80) // Set frame size to 40x40
                    .padding()
                    //.background(Color.blue)
                    .foregroundColor(.white)
                    .clipShape(Circle())
                    .shadow(radius: 5)
            }
            .padding(20)
        }
        .navigationBarTitle("Stocks")
    }
}

struct StocksView_Previews: PreviewProvider {
    static var previews: some View {
        StocksView()
    }
}
