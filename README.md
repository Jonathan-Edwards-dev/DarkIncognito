# DarkIncognito 🕶️

DarkIncognito is a privacy-focused Android browser built to **educate users about the dark web** and provide a **minimal, RAM-only browsing experience** with Tor awareness.

This project is **education-first**.  
It does **not** attempt to make users anonymous, and it does **not** promote illegal activity.

---

## 🚨 Disclaimer

DarkIncognito is created strictly for **learning and awareness purposes**.

- The dark web itself is not illegal
- Misuse of privacy tools can still lead to legal consequences
- Users are fully responsible for how they use this application

DarkIncognito does **not encourage or support illegal activity**.

---

## ✨ What DarkIncognito Does

### 🔐 Privacy-Focused by Design
- RAM-only browsing (no history, cookies, or persistent data)
- No downloads, no saved files
- No form or password saving
- No local cache
- Screenshot blocking enabled
- Session wiped when app is closed, minimized, or backgrounded

### 🌐 Tor Awareness (via Orbot)
- Uses **Orbot** for Tor routing
- Visual Tor connection indicator (connected / not connected)
- Clear warnings when Tor is inactive
- Onion vs clearnet visual cues in the browser UI

### 📚 Education Before Access
- Mandatory educational onboarding
- Explains:
  - What the dark web is
  - How Tor and Orbot work
  - Limitations of mobile Tor browsing
  - Safety and responsibility guidelines
- Users must complete education before entering the browser

### 🧭 Browser Experience
- Clean, Chrome-inspired UI
- Custom omnibox
- Pull-to-refresh
- Multiple tabs
- Per-tab URL handling
- Custom homepage built with HTML/CSS
- Friendly in-app guidance for onion links instead of silent failures

---

## ⚠️ Important Limitations

DarkIncognito is **not** a replacement for Tor Browser.

It does **not**:
- Guarantee anonymity
- Prevent browser fingerprinting
- Protect against advanced tracking
- Bypass all network surveillance
- Provide full Tor isolation

These limitations are **intentionally disclosed** to users inside the app.

---

## 🛠️ Technical Notes

- Written in **Java**
- Android **WebView-based** (custom SecureWebView)
- Tor routing handled externally via **Orbot**
- No APK or release binaries included in this repository
- Keystore, APKs, and build outputs are intentionally excluded

This repository contains **source code only**.

---

## 📦 Running the Project

1. Clone the repository
2. Open in Android Studio
3. Install **Orbot** on the test device
4. Enable Orbot VPN mode
5. Build and run the app

To distribute the app, generate a **signed release APK** using your own keystore.

---

## 👨‍💻 Author

**Jonathan Edwards (JD)**  
Cybersecurity student with interests in:
- Ethical hacking
- Privacy-focused Android development
- Secure application design

DarkIncognito was built to **demystify the dark web** and promote **responsible use of privacy technologies**.

---

## ⭐ Final Note

DarkIncognito is not about hiding from the law.  
It is about **understanding privacy, technology, and responsibility**.

If you study or extend this project, do so ethically.
