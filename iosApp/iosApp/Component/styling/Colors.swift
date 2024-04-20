//
//  Colors.swift
//  iosApp
//

import Foundation
import SwiftUI

//custom divider
struct ColoredDivider: View {
    var color: Color
    var height: CGFloat

    var body: some View {
        Rectangle()
            .fill(color)
            .frame(height: height)
    }
}


let appColor = Color(red: 0.592156862745098, green: 0.5490196078431373, blue: 0.12941176470588237)  //978c21--> COLOR HEXA
let dividerColor = Color(hex: "#CAC4D0")


// Extension to convert hex color to SwiftUI Color
extension Color {
    init(hex: String) {
        var hexSanitized = hex.trimmingCharacters(in: .whitespacesAndNewlines)
        hexSanitized = hexSanitized.replacingOccurrences(of: "#", with: "")

        var rgb: UInt64 = 0

        Scanner(string: hexSanitized).scanHexInt64(&rgb)

        self.init(
            .sRGB,
            red: Double((rgb & 0xFF0000) >> 16) / 255.0,
            green: Double((rgb & 0x00FF00) >> 8) / 255.0,
            blue: Double(rgb & 0x0000FF) / 255.0,
            opacity: 1.0
        )
    }
}
