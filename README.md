# 🖼️ Wallpapers App

A beautiful Android wallpaper application that lets users browse and set high-quality images as wallpapers.

✨ Built with modern Android technologies and a clean Material UI.

---

## 🚀 Features

- 🔍 **Search wallpapers** using keywords
- 📂 **Browse categories** / popular / latest images
- 🖼️ **Full-screen preview** with zoom support
- 📱 **Set wallpaper** (Home screen / Lock screen)
- ⬇️ **Download images** to device
- 🔐 **User authentication** with Firebase
- ❤️ **Favorite wallpapers** synced with Firebase
- 🌙 **Dark / Light theme**
- 📶 **Offline favorites viewing** (cached locally)
- ⚡ Smooth scrolling & modern UI

---

## 📸 Screenshots

(Add screenshots after uploading images to the repo)

<!-- Example placeholders -->

<!-- ![Home Screen](screenshots/home.png) -->
<!-- ![Search](screenshots/search.png) -->
<!-- ![Wallpaper Details](screenshots/details.png) -->

---

## 🛠 Tech Stack

| Category | Technology |
|--------|-----------|
| 💻 Language | Java |
| 🧱 Architecture | MVVM + Clean Architecture |
| 🎨 UI | XML |
| 🖼 Image Loading | Coil |
| 🌐 API | Pixabay API |
| 🔐 Authentication | Firebase Authentication |
| ☁️ Database | Firebase Firestore / Realtime Database |
| 💉 Dependency Injection | Hilt |
| ⚡ Async | Coroutines + Flow |
| 📄 Pagination | Paging 3 |
| 🧭 Navigation | Navigation Component |

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository

```bash
git clone https://github.com/ashraf2k04/Wallpapers.git
cd Wallpapers
```

---

### 2️⃣ Get Pixabay API Key

1. Go to  `https://pixabay.com/ `
2. Create an account → Login  
3. Visit  ` https://pixabay.com/api/docs/ `
4. Copy your **API Key**

---

### 3️⃣ Add API key to project

Open ` local.properties ` in the root folder and add:

```properties
PIXABAY_API_KEY=your_pixabay_api_key_here
```

---

### 4️⃣ Connect Firebase

1. Go to ` https://console.firebase.google.com/ `
2. Create a **new Firebase project**
3. Add an **Android app**
4. Use your package name  
   (example: ` com.ashraf.wallpapers `)
5. Download ` google-services.json `
6. Place it inside the ` app/ ` folder

---

### 5️⃣ Build and run

```bash
./gradlew build
```

Open the project in **Android Studio** and run it on your **device or emulator**.

---

## 📌 Important Notes

⚠️ Pixabay free API is used in this project.

- Attribution not required but appreciated
- Pixabay rate limit ≈ **5000 requests/hour**
- Do not abuse API usage
- Follow Pixabay license terms

Firebase features such as login and favorites are **optional** — the app works even without authentication.

---

## 🤝 Contributing

Pull requests are welcome!

Steps:

1. Fork the project
2. Create a feature branch  
   ( ` git checkout -b feature/amazing-feature ` )
3. Commit your changes  
   ( ` git commit -m "Add amazing feature" ` )
4. Push to your branch  
   ( ` git push origin feature/amazing-feature ` )
5. Open a Pull Request

---

## ⭐ Support

If you like this project:

⭐ Star the repository  
🍴 Fork it  
📢 Share it with others

---

Made with ❤️ using **Kotlin & Android**
