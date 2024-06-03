//import SwiftUI
//import UIKit
//
//struct DepositRequest: View {
//
//    @State private var accountCode = ""
//    @State private var amount = ""
//    @State private var description = ""
//    @State private var bankAccount = ""
//    @State private var depositType = ""
//    @State private var date = ""
//    @State private var transectionRef = ""
//    @State private var showImagePicker = false
//    @State private var showCameraPicker = false
//    @State private var selectedImage: UIImage?
//    @State private var isCamera = false
//
//    var body: some View {
//        ScrollView {
//            VStack() {
//                inputView(text: $accountCode, title: "Account Code", placeHolder: "Select account code")
//                    .padding(.top, 20)
//                inputView(text: $bankAccount, title: "Bank Account", placeHolder: "Enter Bank Account")
//                    .padding(.top, 10)
//                inputView(text: $depositType, title: "Deposit Type", placeHolder: "Select deposit type")
//                    .padding(.top, 10)
//                inputView(text: $date, title: "Date", placeHolder: "DD/MM/YYYY")
//                    .padding(.top, 10)
//                inputView(text: $transectionRef, title: "Transection Ref.", placeHolder: "Enter Transection Reference")
//                    .padding(.top, 10)
//                inputView(text: $amount, title: "Amount", placeHolder: "Enter amount")
//                    .padding(.top, 10)
//                inputView(text: $description, title: "Description", placeHolder: "Write description")
//                    .padding(.top, 10)
//
//                // Image with buttons and labels inside the image border
//                ZStack {
//                    Rectangle()
//                        .strokeBorder(style: StrokeStyle(lineWidth: 1, dash: [5]))
//                        .foregroundColor(appColor)
//                        .frame(width: 307, height: 220)
//
//                    VStack {
//                        if let selectedImage = selectedImage {
//                            Image(uiImage: selectedImage)
//                                .resizable()
//                                .aspectRatio(contentMode: .fit)
//                                .frame(height: 150)
//                        } else {
//                            Image("download-image-logo")
//                                .padding(.top, 10)
//                            Text("Let’s upload receipt")
//                            Text("Upload your receipt for your deposit request from your gallery or open camera")
//                                .font(.subheadline)
//                                .fontWeight(.ultraLight)
//                                .multilineTextAlignment(.center)
//                                .padding(.horizontal, 25)
//                                .foregroundColor(.secondary)
//                        }
//
//                        HStack(spacing: 30) {
//                            VStack {
//                                Button(action: {
//                                    self.isCamera = false
//                                    self.showImagePicker = true
//                                }) {
//                                    Image("Gallary Button")
//                                }
//                            }
//                            VStack {
//                                Button(action: {
//                                    self.isCamera = true
//                                    self.showImagePicker = true
//                                }) {
//                                    Image("Camera Button")
//                                }
//                            }
//                        }
//                        .padding(.bottom, 10)
//                    }
//                }
//                .padding(.top, 20)
//
//                Button(action: {
//                    // Submit action
//                }, label: {
//                    Text("Submit")
//                        .fontWeight(.semibold)
//                        .foregroundColor(.white)
//                        .frame(width: UIScreen.main.bounds.width - 70, height: 48)
//                        .background(Color("AccentColor"))
//                        .cornerRadius(12)
//                })
//                .padding()
//
//                Spacer()
//            }
//            .padding(.horizontal, 20)
//        }
//        .navigationBarTitle("Deposit Request", displayMode: .inline)
//        .navigationBarItems(
//            leading: Button(action: {
//                // Action for the first button
//            }) {
//                Image("loading_nav_button")
//            },
//            trailing: HStack {
//                Button(action: {
//                    // Action for the second button
//                    // Show your alert or perform an action
//                }) {
//                    Image("search_nav_button")
//                }
//                Button(action: {
//                    // Action for another button
//                }) {
//                    Image("alarm_nav_button")
//                }
//            }
//        )
//        .sheet(isPresented: $showImagePicker) {
//            ImagePickerforDeposits(sourceType: self.isCamera ? .camera : .photoLibrary, selectedImage: self.$selectedImage)
//        }
//    }
//}
//
//// ImagePickerforDeposits to handle photo library and camera
//struct ImagePickerforDeposits: UIViewControllerRepresentable {
//    enum SourceType {
//        case photoLibrary
//        case camera
//    }
//
//    var sourceType: SourceType
//    @Binding var selectedImage: UIImage?
//
//    func makeCoordinator() -> Coordinator {
//        return Coordinator(self)
//    }
//
//    func makeUIViewController(context: Context) -> UIImagePickerController {
//        let picker = UIImagePickerController()
//        picker.delegate = context.coordinator
//        picker.sourceType = (sourceType == .camera) ? .camera : .photoLibrary
//        return picker
//    }
//
//    func updateUIViewController(_ uiViewController: UIImagePickerController, context: Context) {}
//
//    class Coordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
//        var parent: ImagePickerforDeposits
//
//        init(_ parent: ImagePickerforDeposits) {
//            self.parent = parent
//        }
//
//        func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
//            if let image = info[.originalImage] as? UIImage {
//                parent.selectedImage = image
//            }
//            picker.dismiss(animated: true)
//        }
//    }
//}
//
//#Preview {
//    DepositRequest()
//}
import SwiftUI
import UIKit

// Wrapper struct to conform to Identifiable
struct CameraSource: Identifiable {
    let id = UUID()
    let isCamera: Bool
}

struct DepositRequest: View {
    
    @State private var accountCode = ""
    @State private var amount = ""
    @State private var description = ""
    @State private var bankAccount = ""
    @State private var depositType = ""
    @State private var date = Date()
    @State private var transectionRef = ""
    @State private var showImagePicker = false
    @State private var selectedImage: UIImage?
    @State private var cameraSource: CameraSource? = nil // Use an optional CameraSource
    @State private var showDatePicker = false // State variable to show date picker
    @State private var showDepositTypePicker = false // State variable to show deposit type picker
    @State private var depositTypes = ["a","v","c"]
    var body: some View {
        ScrollView {
            VStack {
                inputView(text: $accountCode, title: "Account Code", placeHolder: "Select account code")
                    .padding(.top, 20)
                inputView(text: $bankAccount, title: "Bank Account", placeHolder: "Enter Bank Account")
                    .padding(.top, 10)
                //inputView(text: $depositType, title: "Deposit Type", placeHolder: "Select deposit type")
                // Deposit Type input view with Menu for drop-down
                             VStack(alignment: .leading) {
                                 Text("Deposit Type")
                                     .fontWeight(.semibold)
                                     .foregroundColor(.primary)
                                     .font(.footnote)
                                 
                                 Menu {
                                     ForEach(depositTypes, id: \.self) { type in
                                         Button(action: {
                                             depositType = type
                                         }) {
                                             Text(type)
                                         }
                                     }
                                 } label: {
                                     HStack {
                                         Text(depositType.isEmpty ? "Select deposit type" : depositType)
                                             .foregroundColor(depositType.isEmpty ? .gray : .primary)
                                         Spacer()
                                         Image(systemName: "chevron.down")
                                             .foregroundColor(.gray)
                                     }
                                 }
                                 Divider()
                                     .overlay(.primary)
                             }
                .padding(.top, 10)
                .sheet(isPresented: $showDepositTypePicker) {
                    VStack {
                        Text("Select Deposit Type")
                            .font(.headline)
                            .padding()
                        
                        Picker("Deposit Type", selection: $depositType) {
                            ForEach(depositTypes, id: \.self) { type in
                                Text(type).tag(type)
                            }
                        }
                        .labelsHidden()
                        .pickerStyle(WheelPickerStyle())
                        
                        Button("Done") {
                            showDepositTypePicker = false
                        }
                        .padding()
                    }
                }
                
                .padding(.top, 10)
                //inputView(text: $date, title: "Date", placeHolder: "DD/MM/YYYY")
                // Date input view with tap gesture to show date picker
                VStack(alignment: .leading) {
                    Text("Date")
                        .fontWeight(.semibold)
                        .foregroundColor(.primary)
                        .font(.footnote)
                    
                    HStack {
                        Text(date, style: .date) // Display selected date
                            .foregroundColor(date == Date() ? .gray : .primary)
                            .onTapGesture {
                                showDatePicker.toggle() // Toggle date picker visibility
                            }
                        
                        Spacer()
                        Image("calender_icon")
                            .foregroundColor(.gray)
                            .onTapGesture {
                                showDatePicker.toggle() // Toggle date picker visibility
                            }
                    }
                    Divider()
                        .overlay(.primary)
                }
                .padding(.top, 10)
                inputView(text: $transectionRef, title: "Transection Ref.", placeHolder: "Enter Transection Reference")
                    .padding(.top, 10)
                inputView(text: $amount, title: "Amount", placeHolder: "Enter amount")
                    .padding(.top, 10)
                inputView(text: $description, title: "Description", placeHolder: "Write description")
                    .padding(.top, 10)
                
                // Image with buttons and labels inside the dashed border
                ZStack {
                    Rectangle()
                        .strokeBorder(style: StrokeStyle(lineWidth: 1, dash: [5]))
                        .foregroundColor(appColor)
                        .frame(width: 307, height: 220)
                    
                    VStack {
                        if let selectedImage = selectedImage {
                            Image(uiImage: selectedImage)
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .frame(height: 150)
                        } else {
                            Image("download-image-logo")
                                .padding(.top, 10)
                            Text("Let’s upload receipt")
                            Text("Upload your receipt for your deposit request from your gallery or open camera")
                                .font(.subheadline)
                                .fontWeight(.ultraLight)
                                .multilineTextAlignment(.center)
                                .padding(.horizontal, 25)
                                .foregroundColor(.secondary)
                        }
                        
                        HStack(spacing: 30) {
                            VStack {
                                Button(action: {
                                    self.cameraSource = CameraSource(isCamera: false) // Set isCamera to false for Gallery button
                                    self.showImagePicker = true
                                }) {
                                    Image("Gallary Button")
                                }
                            }
                            VStack {
                                Button(action: {
                                    self.cameraSource = CameraSource(isCamera: true) // Set isCamera to true for Camera button
                                    self.showImagePicker = true
                                }) {
                                    Image("Camera Button")
                                }
                            }
                        }
                        .padding(.bottom, 10)
                    }
                }
                .padding(.top, 20)
                
                Button(action: {
                    // Submit action
                }, label: {
                    Text("Submit")
                        .fontWeight(.semibold)
                        .foregroundColor(.white)
                        .frame(width: UIScreen.main.bounds.width - 70, height: 48)
                        .background(Color("AccentColor"))
                        .cornerRadius(12)
                })
                .padding()
                
                Spacer()
            }
            .padding(.horizontal, 20)
        }
        .navigationBarTitle("Deposit Request", displayMode: .inline)
        .navigationBarItems(
            leading: Button(action: {
                // Action for the first button
            }) {
                Image("loading_nav_button")
            },
            trailing: HStack {
                Button(action: {
                    // Action for the second button
                    // Show your alert or perform an action
                }) {
                    Image("search_nav_button")
                }
                Button(action: {
                    // Action for another button
                }) {
                    Image("alarm_nav_button")
                }
            }
        )
        .sheet(item: $cameraSource) { cameraSource in
            if cameraSource.isCamera {
                ImagePickerforDeposits(sourceType: .camera, selectedImage: self.$selectedImage)
            } else {
                ImagePickerforDeposits(sourceType: .photoLibrary, selectedImage: self.$selectedImage)
            }
        }
        .sheet(isPresented: $showDatePicker) {
            DatePicker(
                "Select Date",
                selection: $date,
                displayedComponents: [.date]
            )
            .datePickerStyle(GraphicalDatePickerStyle())
            .padding()
        }
    }
}

// ImagePickerforDeposits to handle photo library and camera
struct ImagePickerforDeposits: UIViewControllerRepresentable {
    enum SourceType {
        case photoLibrary
        case camera
    }
    
    var sourceType: SourceType
    @Binding var selectedImage: UIImage?
    
    func makeCoordinator() -> Coordinator {
        return Coordinator(self)
    }
    
    func makeUIViewController(context: Context) -> UIImagePickerController {
        let picker = UIImagePickerController()
        picker.delegate = context.coordinator
        picker.sourceType = (sourceType == .camera) ? .camera : .photoLibrary
        return picker
    }
    
    func updateUIViewController(_ uiViewController: UIImagePickerController, context: Context) {}
    
    class Coordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
        var parent: ImagePickerforDeposits
        
        init(_ parent: ImagePickerforDeposits) {
            self.parent = parent
        }
        
        func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
            if let image = info[.originalImage] as? UIImage {
                parent.selectedImage = image
            }
            picker.dismiss(animated: true)
        }
    }
}

#Preview {
    DepositRequest()
}
