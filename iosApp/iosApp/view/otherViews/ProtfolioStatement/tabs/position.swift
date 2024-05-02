//
//  position.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 9/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI


struct positionRowViews: View {
    var titles: [String]
    var values: [String]
    var colors: [Color]
    
    var body: some View {
        VStack {
            ForEach(Array(zip(titles, zip(values, colors))), id: \.0) { title, valueColor in
                HStack {
                    Text("\(title)")
                        .foregroundColor(valueColor.1) // Use color from the colors array
                        .bold()
                    Spacer()
                    Text(valueColor.0)
                        .foregroundColor(getValueColor(valueColor.0))
                }
            }
        }
        .padding()
        .background(Color.white)
        .cornerRadius(10)
        .shadow(radius: 5)
    }
    
    // Function to get color for value based on its content
    private func getValueColor(_ value: String) -> Color {
        if value.contains("+") {
            return .green
        }
        else if value.contains("-"){
            return .red
        }
        else {
            return .black
        }
    }
}
struct positions: View {
    // Define arrays for titles, values, and colors
    let titles = [
                  ["DSEX","DSEX index|DSE"],
                  ["CCX","DSEX index|DCCS"]]
    
    let values = [
                  ["6,000","+1835546"],
                  ["2,000","-2003"]
                  ]
    let colors: [[Color]] = [
        [.black, .gray],
        [.black, .gray]
    ]
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                // Iterate over titles, values, and colors arrays
                ForEach(Array(zip(titles, zip(values, colors))), id: \.0) { titleRow, valueColorRow in
                    positionRowViews(titles: titleRow, values: valueColorRow.0, colors: valueColorRow.1)
                }
            }
            .padding(.top, 20) // Add padding to the top
            .padding(.bottom, 20) // Add padding to the bottom
            .padding(.horizontal, 20)
        }
    }
}

#Preview {
    positions()
}
