# DarkIncognito 🕶️

DarkIncognito is a privacy-focused Android browser built to **educate users about the dark web** and provide a **minimal, RAM-only browsing experience** with Tor awareness.

This project is **education-first**.  
It is not designed to enable illegal activity, nor does it claim to provide full anonymity.

---

## 🚨 Disclaimer

DarkIncognito is created strictly for **learning and awareness purposes**.

- The dark web itself is not illegal
- Misuse of privacy tools can still lead to legal consequences
- Users are fully responsible for how they use this application

DarkIncognito does **not encourage or support illegal activity**.

---

## ✨ What DarkIncognito (v1) Provides

### 🔐 Privacy-Oriented Design
- RAM-only browsing (no history, cookies, or persistent storage)
- No downloads
- No saved files
- No form or password saving
- No WebView caching
- Screenshot blocking enabled
- Session wiped when app is closed, minimized, or backgrounded

### 🌐 Tor Awareness (via Orbot)
- Uses **Orbot** (VPN mode) for Tor routing
- Visual Tor connection indicator
- Clear warning when Tor is inactive
- Onion vs clearnet visual cues in the UI

### 📚 Mandatory Education Flow
- Users must complete an educational onboarding before accessing the browser
- Explains:
  - What the dark web is
  - How Tor and Orbot work
  - The limitations of mobile Tor browsing
  - Responsible and safe usage practices
- No shortcuts to bypass education

### 🧭 Browser Experience
- Clean, Chrome-inspired UI
- Custom omnibox
- Pull-to-refresh
- Multiple tabs
- Per-tab URL handling
- Custom HTML/CSS homepage
- Friendly in-app guidance for onion links instead of silent failures

---

## ⚠️ Known Limitations (By Design)

DarkIncognito **is NOT a Tor Browser replacement**.

It does **not**:
- Guarantee anonymity
- Prevent browser fingerprinting
- Protect against advanced tracking
- Isolate Tor circuits per tab
- Provide Tor-native DNS resolution
- Offer hardened JavaScript isolation

These limitations are **explicitly disclosed** to users inside the app.

---

## 🛠️ Technical Notes

- Language: Java
- UI: XML + custom HTML/CSS assets
- Web engine: Android WebView (SecureWebView)
- Tor routing: Orbot (external)
- No APK, AAB, or keystore files are included in this repository
- Build outputs and signing materials are intentionally excluded

This repository contains **source code only**.

---

# 🔮 DarkIncognito-X (Planned)

DarkIncognito-X is a planned next-generation evolution of DarkIncognito, designed to address architectural and security limitations inherent in Android WebView–based browsers.

While DarkIncognito prioritizes education, accessibility, and responsible onboarding to Tor, DarkIncognito-X moves toward a **Tor-native browser architecture**, closer in spirit and behavior to the official Tor Browser—while exploring areas where a mobile-first design can go further.



## Why DarkIncognito-X Is Different

DarkIncognito-X is planned to use **GeckoView**, the same rendering engine family used by Firefox and Tor Browser, instead of Android’s system WebView.

This architectural shift enables:

- Stronger resistance to browser fingerprinting  
- Greater control over JavaScript behavior and exposed APIs  
- More predictable, Tor-aligned browser characteristics  
- Reduced dependency on device-specific WebView implementations  
- Improved security boundaries compared to system WebView usage  

DarkIncognito-X **inherits all core privacy and security features from DarkIncognito**, including its strict RAM-only session model.



## Relationship to Tor Browser

DarkIncognito-X aims to move significantly closer to Tor Browser behavior, while maintaining clear and honest scope boundaries.

**What DarkIncognito-X aims to provide:**
- Closer rendering behavior to Tor Browser  
- Improved privacy characteristics compared to WebView-based browsers  
- Stronger mobile-focused privacy guarantees than Tor Browser  

**What DarkIncognito-X does *not* aim to be:**
- A full desktop Tor Browser replacement  
- A guarantee of anonymity or deanonymization resistance  

DarkIncognito-X is intended for **advanced users, researchers, and experimentation**, not casual or first-time Tor users.



## Where DarkIncognito-X Can Go Further Than Tor Browser

DarkIncognito-X is not designed to replace Tor Browser—but in certain areas, it intentionally explores capabilities that Tor Browser does not prioritize, especially on Android.

Potential areas of elevation include:

- **Mobile-first privacy enforcement**  
- **More aggressive fingerprint surface reduction**  
- **Research-oriented flexibility and experimentation**  
- **Clearer, deeper threat-model documentation**

These elevations are **intentional trade-offs**, not claims of universal superiority.



## Clear Distinction Between Projects

### DarkIncognito
- Focuses on education and awareness  
- Provides a responsible introduction to Tor on Android  

### DarkIncognito-X
- Focuses on technical hardening and experimentation  
- Targets users who understand Tor’s threat model and limitations  

DarkIncognito-X is an **evolution**, not a replacement.



## Project Status

DarkIncognito-X is currently **planned and under design exploration**.  
Details may evolve as research and development progress.


---

## ⭐ Final Note

DarkIncognito is not about hiding from the law.  
It is about **understanding privacy, technology, and responsibility**.

If you study or extend this project, do so ethically.
