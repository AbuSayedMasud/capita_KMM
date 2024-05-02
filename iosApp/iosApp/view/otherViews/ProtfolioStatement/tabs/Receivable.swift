import SwiftUI

struct ReceivableData: Identifiable {
    let id = UUID()
    let title: String
    let title2: String
    let value1: String
    let title3: String
    let value2: String
}

struct Receivable: View {
    // Define arrays for titles, title2s, values1, title3s, and values2
    let data: [ReceivableData] = [
        ReceivableData(title: "Right Share Receivable", title2: "DSEX index", value1: "6,000", title3: "DSE", value2: "+1835546"),
        ReceivableData(title: "CCX Receivable", title2: "DSEX index", value1: "2,000", title3: "DCCS", value2: "-2003"),
        ReceivableData(title: "CCX Receivable", title2: "DSEX index", value1: "2,000", title3: "DCCS", value2: "-2003"),
        ReceivableData(title: "CCX Receivable", title2: "DSEX index", value1: "2,000", title3: "DCCS", value2: "-2003")
    ]
    
    var body: some View {
        ScrollView {
            VStack(spacing: 20) {
                // Iterate over data array
                ForEach(data) { item in
                    RecebableViews(data: item, color: .black)
                }
            }
            .padding(.top, 20) // Add padding to the top
            .padding(.bottom, 20) // Add padding to the bottom
            .padding(.horizontal, 20)
        }
    }
}

struct RecebableViews: View {
    var data: ReceivableData
    var color: Color
    
    var body: some View {
        VStack {
            HStack {
                Text(data.title)
                    .foregroundColor(.black) // Use color provided
                    .bold()
                Spacer()
            }
            Spacer()
            Spacer()
            HStack {
                Text(data.title2)
                    .foregroundColor(.gray)
                Spacer()
                Text("Value 1: \(data.value1)")
                    .foregroundColor(.gray) // Determine color dynamically based on value
            }
            HStack {
                Text(data.title3)
                    .foregroundColor(.gray)
                Spacer()
                Text("Value 2: \(data.value2)")
                    .foregroundColor(.gray) // Determine color dynamically based on value
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


struct Receivable_Previews: PreviewProvider {
    static var previews: some View {
        Receivable()
    }
}
