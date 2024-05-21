////
////  position.swift
////  iosApp
////
////  Created by LEADS Corporation Limited on 9/4/24.
////  Copyright © 2024 orgName. All rights reserved.
////
//
import SwiftUI

struct PositionRowView: View {
    var title: String
    var value: String
    var color: Color
    var costPrice: Double?
    var unrealizedGain: Double?
    var costValue: Double?
    var marketPrice: Double?
    var matureQuantity: Int?
    var quantity: Int?
    var marketValue: Double?
    
    @Binding var isExpanded: Bool
    
    var body: some View {
        VStack {
            HStack {
                Text("\(title)")
                    .foregroundColor(color)
                    .bold()
                Spacer()
                Text(value)
                    .foregroundColor(getValueColor(value))
            }
            if isExpanded {
                if let costPrice = costPrice {
                    detailRow(label: "Cost Price:", value: String(format: "%.2f", costPrice))
                }
                if let costValue = costValue {
                    detailRow(label: "Cost Value:", value: String(format: "%.2f", costValue))
                }
               
                if let marketPrice = marketPrice {
                    detailRow(label: "Market Price:", value: String(format: "%.2f", marketPrice))
                }
                if let marketValue = marketValue {
                    detailRow(label: "Market Value:", value: String(format: "%.2f", marketValue))
                }
                if let matureQuantity = matureQuantity {
                    detailRow(label: "Mature Quantity:", value: "\(matureQuantity)")
                }
                if let quantity = quantity {
                    detailRow(label: "Quantity:", value: "\(quantity)")
                }
               
                if let unrealizedGain = unrealizedGain {
                    detailRow(label: "Unrealized Gain:", value: String(format: "%.2f", unrealizedGain))
                }
               
            }
        }
        .padding()
        .background(Color.white)
        .cornerRadius(10)
        .shadow(radius: 5)
        .onTapGesture {
            withAnimation {
                isExpanded.toggle()
            }
        }
    }
    
    private func detailRow(label: String, value: String) -> some View {
        HStack {
            Text(label)
                .foregroundColor(.gray)
            Spacer()
            Text(value)
                .foregroundColor(.gray)
        }
        .padding(.top, 5)
    }

    // Function to get color for value based on its content
    private func getValueColor(_ value: String) -> Color {
        if value.contains("+") {
            return .green
        } else if value.contains("-") {
            return .red
        } else {
            return .black
        }
    }
}

struct Positions: View {
    @ObservedObject var instrumentViewModel: InstrumentViewModel
    @State private var expandedIndex: Int? = nil

    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                ForEach(instrumentViewModel.instrumentsList.indices, id: \.self) { index in
                    let instrument = instrumentViewModel.instrumentsList[index]
                    if let symbol = instrument["symbole"] as? String,
                       let marketPrice = instrument["marketPrice"] as? Double,
                       let costPrice = instrument["costPrice"] as? Double,
                       let unrealizedGain = instrument["unrealizedGain"] as? Double,
                       let costValue = instrument["costValue"] as? Double,
                       let matureQuantity = instrument["matureQuantity"] as? Int,
                       let quantity = instrument["quantity"] as? Int,
                       let marketValue = instrument["marketValue"] as? Double {
                        
                        let title = "\(symbol)"
                        let value = " "//String(format: "%.2f", marketPrice)
                        let color: Color = .black
                        
                        PositionRowView(
                            title: title,
                            value: value,
                            color: color,
                            costPrice: costPrice,
                            unrealizedGain: unrealizedGain,
                            costValue: costValue,
                            marketPrice: marketPrice,
                            matureQuantity: matureQuantity,
                            quantity: quantity,
                            marketValue: marketValue,
                            isExpanded: Binding(
                                get: { expandedIndex == index },
                                set: { newValue in
                                    expandedIndex = newValue ? index : nil
                                }
                            )
                        )
                    }
                }
            }
            .padding(.top, 20)
            .padding(.bottom, 20)
            .padding(.horizontal, 20)
        }
    }
}

#Preview {
    Positions(instrumentViewModel: InstrumentViewModel())
}
