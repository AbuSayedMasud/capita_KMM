//
//  all_statements.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 15/4/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct all_statementsRowViews: View {
    var titles: [String]
    var values: [String]
    var colors: [Color]
    
    var body: some View {
        VStack {
            ForEach(Array(zip(titles, zip(values, colors))), id: \.0) { title, valueColor in
                HStack {
                    Text("\(title)")
                        .foregroundColor(valueColor.1)
                        .bold()

                    Spacer()
                    Text(valueColor.0)
                        .foregroundColor(getValueColor(valueColor.0))
                }
            }
        }
        .padding()
        //.background(Color.white)
        .background(Color(UIColor(named: "sectionTheme")!))
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
            return .gray
        }
    }
}

struct all_statements: View {
    // Define arrays for titles, values, and colors
    let titles = [
                  ["DSEX","DSEX index|DSE","10-Apr-2024"],
                  ["CCX","","15-Apr-2004"],
                  ["DSEX1","DSEX index|DSE","12-Apr-2024"],
                  ["DSEX2","","05-jan-2021"],
                  ["DSEX3","","15-Apr-2023"]]
    
    let values = [
                  ["-6,000","@7668",""],
                  ["-2,000","",""],
                  ["+6,000","@23456",""],
                  ["-2,000","",""],
                  ["+6,000","",""]
                  ]
    let colors: [[Color]] = [
        [.primary, .gray, .gray],
        [.primary, .gray, .gray],
        [.primary, .gray, .gray],
        [.primary, .gray, .gray],
        [.primary, .gray, .gray]
    ]
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                // Iterate over titles, values, and colors arrays
                ForEach(Array(zip(titles, zip(values, colors))), id: \.0) { titleRow, valueColorRow in
                    all_statementsRowViews(titles: titleRow, values: valueColorRow.0, colors: valueColorRow.1)
                }
            }
            .padding(.top, 20) // Add padding to the top
            .padding(.bottom, 20) // Add padding to the bottom
            .padding(.horizontal, 20)
        }
    }
}

#Preview {
    all_statements()
}
