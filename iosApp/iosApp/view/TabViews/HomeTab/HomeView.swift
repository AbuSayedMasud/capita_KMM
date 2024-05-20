//
//  shantaHomeView.swift
//  iosApp
//
//  Created by LEADS Corporation Limited on 10/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct shantaHomeView: View {
    @ObservedObject var AccountBalnceViewModel: AccountBalanceViewModel
    @ObservedObject var instrumentViewModel: InstrumentViewModel
    
    init() {
        // Customize navigation bar appearance
        let appearance = UINavigationBarAppearance()
        appearance.configureWithOpaqueBackground()
        let color = UIColor(red: 0.592156862745098, green: 0.5490196078431373, blue: 0.12941176470588237, alpha: 1.0)
        appearance.backgroundColor = color
        
        
        UINavigationBar.appearance().standardAppearance = appearance
        UINavigationBar.appearance().scrollEdgeAppearance = appearance
        
        self.AccountBalnceViewModel = AccountBalanceViewModel()
        self.instrumentViewModel = InstrumentViewModel()
    }
    
    //@ObservedObject private var viewModel: ContentViewModel//AccountBalanceViewModel
    //
    //      init(viewModel: ContentViewModel) {
    //          self.viewModel = viewModel
    //      }
    
    
    
    //    init() {
    //
    //        self.AccountBalnceViewModel = AccountBalanceViewModel()
    //    }
    //
    // Define arrays for news titles, details, and dates
    let newsTitles = ["ABBANK: Weekly NAV", "ACI: Dividend Disbursement"]
    let newsDetails = [
        "SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead",
        "SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead SubAPSCL NonConvertible and Fully Redeemable Couphead"
    ]
    let newsDates = ["15-jun-2023", "15-jun-2023"]
    
    var body: some View {
        NavigationView {
            ScrollView {
                VStack(spacing: 20) {
                    VStack {
                        NavigationLink(destination: Protfolio_statement(defaultSelectedTabIndex: 1)) {
                            HStack{
                                Text("Balance")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        HStack {
                            Text("Available Balance")
                            //.fontWeight(.medium)
                            
                            Spacer()
                            
                            Text(String(AccountBalnceViewModel.cashBalance))
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("Current Balance")
                            Spacer()
                            Text(String(AccountBalnceViewModel.currentBalance))
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        HStack {
                            Text("Equtity")
                            Spacer()
                            Text(String(AccountBalnceViewModel.equity))
                        }
                        
                        ColoredDivider(color: dividerColor, height: 1)
                        HStack {
                            Text("Purchase Power")
                            Spacer()
                            Text(String(AccountBalnceViewModel.buyingPower))
                        }
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5) // Add shadow here
                    
                    VStack {
                        NavigationLink(destination: Protfolio_statement(defaultSelectedTabIndex: 0)) {
                            HStack{
                                Text("Position")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        
                        ForEach(instrumentViewModel.instrumentsList.indices.prefix(2), id: \.self) { index in
                            let instrument = instrumentViewModel.instrumentsList[index]
                            if let symbole = instrument["symbole"] as? String,
                                   let marketPrice = instrument["marketPrice"] as? Double,
                               let costPrice = instrument["costPrice"] as? Double {
                                HStack {
                                    VStack(alignment: .leading) {
                                        if let symbole = instrument["symbole"] as? String {
                                            Text(symbole)
                                        }
                                        
                                        Text("Market Price")
                                            .font(.caption)
                                            .foregroundColor(.gray)
                                        
                                        
                                        Text("Cost Price")
                                            .font(.caption)
                                            .foregroundColor(.gray)
                                        
                                    }
                                    Spacer()
                                    VStack(alignment: .trailing) {
                                        Text("")
                                        Text(String(format: "%.2f", marketPrice))
                                            .font(.caption)
                                            .foregroundColor(.gray)
                                        
                                        Text(String(format: "%.2f", costPrice))
                                            .font(.caption)
                                            .foregroundColor(.gray)
                                    }
                                }
                                // Show the divider after the first instrument
                                if index == 0 {
                                    ColoredDivider(color: dividerColor, height: 1)
                                }
                                
                            }
                            //ColoredDivider(color: dividerColor, height: 1)
                        }
                        
                    }
                    
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                    
                    
                    VStack {//3rd vstack
                        NavigationLink(destination: Protfolio_statement(defaultSelectedTabIndex: 2)) {
                            HStack{
                                Text("Receivable")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        HStack {
                            Text("Right Share Receivable")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("AL-HAJTEX")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("100.00 @ 0.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        HStack {
                            Text("AL-HAJTEX")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("50.00 @ 0.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("Cash Dividend Receivable")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("ACI")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("0.00 @ 22,000.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        HStack {
                            Text("ACI")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("0.00 @ 22,000.00")
                                .font(.caption)
                                .foregroundColor(.gray)
                        }
                        
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                    
                    VStack {
                        NavigationLink(destination: Ledger_Statement()) {
                            HStack{
                                Text("Transaction")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        HStack {
                            Text("Cash Deposit")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                            
                            Text("-20,000.00")
                                .foregroundColor(Color.red)
                                .bold()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("15-jun-2023")
                                .foregroundColor(.gray)
                            Spacer()
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("cdbl charge Ac opening (own)")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                            
                            Text("+500.00")
                                .foregroundColor(Color.green)
                                .bold()
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("15-jun-2023")
                                .foregroundColor(.gray)
                            Spacer()
                        }
                        ColoredDivider(color: dividerColor, height: 1)
                        
                        HStack {
                            Text("BUY - ABBANK")
                                .foregroundColor(Color.black)
                                .bold()
                            Spacer()
                            
                            Text("+7,575.00")
                                .foregroundColor(Color.green)
                                .bold()
                        }
                        HStack {
                            Text("")
                        }
                        
                        HStack {
                            Text("SubAPSCL NonConvertIABLE and Fully Redeemable Couphead")
                                .foregroundColor(.gray)
                            Spacer()
                            Text("500 @ 15")
                                .foregroundColor(.gray)
                        }
                        HStack {
                            Text("")
                        }
                        HStack {
                            Text("15-jun-2023")
                                .foregroundColor(.gray)
                            Spacer()
                        }
                        
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                    
                    // News Section
                    VStack {
                        NavigationLink(destination: marketView(defaultSelectedTabIndex: 4)) {
                            HStack{
                                Text("News")
                                    .foregroundColor(Color.black)
                                    .bold()
                                
                                Spacer()
                                Image(uiImage: UIImage(named: "next_page_arrow")!)
                                    .resizable()
                                    .frame(width: 8, height: 12)
                                    .foregroundColor(.accentColor)
                            }
                        }
                        
                        Divider()
                            .background(Color(red: 0.929, green: 0.929, blue: 0.929))
                            .padding(.horizontal, -20)
                        
                        // Iterate over the news arrays to populate the news section dynamically
                        ForEach(0..<newsTitles.count) { index in
                            VStack {
                                HStack {
                                    Text(self.newsTitles[index])
                                        .foregroundColor(Color.black)
                                        .bold()
                                    Spacer()
                                }
                                HStack {
                                    Text("")
                                }
                                HStack {
                                    Text(self.newsDetails[index])
                                        .foregroundColor(.gray)
                                }
                                HStack {
                                    Text("")
                                }
                                HStack {
                                    Text(self.newsDates[index])
                                        .foregroundColor(.gray)
                                    Spacer()
                                }
                            }
                            
                            // Add divider only if it's not the last news item
                            if index != self.newsTitles.count - 1 {
                                ColoredDivider(color: dividerColor, height: 1)
                            }
                        }
                    }
                    .padding()
                    .background(Color.white)
                    .cornerRadius(10)
                    .shadow(radius: 5)
                }//full-vstack
                .padding(.top, 20)
                .padding(.horizontal, 20)
            }
            
            .navigationBarTitle("SHANTA", displayMode: .inline)
            .navigationBarItems(
                leading:
                    Button(action: {
                        // Handle action for the first button
                    }) {
                        Image(uiImage: UIImage(named: "loading_nav_button")!)
                    },
                trailing:
                    Button(action: {
                        // Handle action for the second button
                    }) {
                        Image(uiImage: UIImage(named: "alarm_nav_button")!)
                    }
            )
        }//navigationview
        
    }
}

#Preview {
    shantaHomeView()
}
