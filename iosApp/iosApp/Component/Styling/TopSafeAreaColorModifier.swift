//
//  TopSafeAreaColorModifier.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 10/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct TopSafeAreaColorModifier: ViewModifier {
    func body(content: Content) -> some View {
        content
            .padding(.top, 1)
            .background(appColor.edgesIgnoringSafeArea(.top))
    }
}

extension View {
    func topSafeAreaColor() -> some View {
        self.modifier(TopSafeAreaColorModifier())
    }
}
