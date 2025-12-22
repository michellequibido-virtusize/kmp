import SwiftUI
import shared

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        ComposeUIViewControllerWrapper()
                   .ignoresSafeArea()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
