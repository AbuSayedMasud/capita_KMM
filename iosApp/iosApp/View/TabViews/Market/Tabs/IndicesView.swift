import SwiftUI

struct IndicesRow: View {
    var name: String
    var value: String
    var title: String
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

struct IndicesView: View {
    let indices = [
        ("Index1", "Value1", "Title1", "Value2_1"),
        ("Index2", "Value2", "Title2", "Value2_2"),
        ("Index3", "Value3", "Title3", "Value2_3"),
        ("Index4", "Value4", "Title4", "Value2_4"),
        ("Index5", "Value5", "Title5", "Value2_5"),
        ("Index6", "Value6", "Title6", "Value2_6"),
        ("Index7", "Value7", "Title7", "Value2_7"),
        ("Index8", "Value8", "Title8", "Value2_8"),
        ("Index9", "Value9", "Title9", "Value2_9"),
        ("Index10", "Value10", "Title10", "Value2_10")
    ]
    
    var body: some View {
        ZStack(alignment: .bottomTrailing) {
            ScrollView {
                VStack(spacing: 16) {
                    ForEach(indices, id: \.self.0) { index in
                        IndicesRow(name: index.0, value: index.1, title: index.2, value2: index.3)
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
                    //.background(Color(UIColor(named: "sectionTheme")!))
                    .clipShape(Circle())
                    .shadow(radius: 5)
            }
            .padding(20)
        }
        .navigationBarTitle("Indices")
    }
}

struct IndicesView_Previews: PreviewProvider {
    static var previews: some View {
        IndicesView()
    }
}
