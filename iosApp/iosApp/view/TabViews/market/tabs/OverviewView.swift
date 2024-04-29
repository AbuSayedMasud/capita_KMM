//
//  OverviewView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 11/3/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct OverviewView: View {
    // Define arrays for titles, values, and colors
    let titles = [["Market", "Market Status", "Last Update"],
                  ["DSEX","DSEX index|DSE"],
                  ["CCX","DSEX index|DCCS"],
                  ["TTEX","DSEX index|RTGF"],
                  ["RREX","DSEX index|DSSE"],
                  ["Total Trade", "Total Volume", "Total Value in Taka"], ["Issues", "Issues2", "Issues3"]]
    let values = [["Value1", "Value2", "Value3"],
                  ["6,000","+1835546"],
                  ["2,000","+2003"],
                  ["4,000","-143"],
                  ["7,000","-183"],
                  ["+Value4", "-Value5", "Value6"], ["Value7", "Value8", "Value9"]]
    let colors: [[Color]] = [
        [.black, .black, .black],
        [.black, .gray],
        [.black, .gray],
        [.black, .gray],
        [.black, .gray],
        [.black, .black, .black],
        [.black, .black, .black]
    ]
    
    var body: some View {
        ZStack(alignment: .bottomTrailing) {
            ScrollView {
                VStack(spacing: 20) {
                    // Iterate over titles, values, and colors arrays
                    ForEach(Array(zip(titles, zip(values, colors))), id: \.0) { titleRow, valueColorRow in
                        RowViews(titles: titleRow, values: valueColorRow.0, colors: valueColorRow.1)
                    }
                }
                .padding(.top, 20) // Add padding to the top
                .padding(.bottom, 20) // Add padding to the bottom
                .padding(.horizontal, 20)
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
    }
}

struct RowViews: View {
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

struct OverviewView_Previews: PreviewProvider {
    static var previews: some View {
        OverviewView()
    }
}
