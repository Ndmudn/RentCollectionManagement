# RentCollectionManagement - Android (v3.0) Skeleton

This branch contains an initial Android Studio project skeleton for A.H Palace Rent & Property Manager (v3.0) using Kotlin + Jetpack Compose + Firebase.

What this commit includes:
- Gradle Kotlin DSL (root + app)
- Compose BoM and Firebase BoM dependencies
- Hilt DI setup
- Basic NavGraph + Login / OTP / Dashboard screens (skeleton)
- AuthViewModel skeleton using FirebaseAuth
- Room database entity + DAO for offline_payment_queue
- WorkManager CoroutineWorker stub for SyncOfflinePaymentsWorker
- RecordPayment screen and ViewModel with mandatory Transaction ID validation in ViewModel

Next tasks (PR checklist):
- Add `google-services.json` to `app/` (dev/staging)
- Implement full Phone OTP flow using `PhoneAuthProvider` callbacks
- Wire Room DAO usage in RecordPaymentViewModel to enqueue offline payments
- Implement SyncOfflinePaymentsWorker to call Cloud Function / Firestore with idempotency key
- Add UI polish, theming, Material3 components, and icons
- Add tests: unit tests for ViewModel, instrumentation tests for WorkManager & Room
- Configure CI (GitHub Actions) to run `./gradlew assembleDebug` and unit tests

How to test locally:
1. Open project in Android Studio (recommended: Hedgehog or newer)
2. Add `google-services.json` to `app/`
3. Build & run on a physical device (for FCM & biometrics)

If you want I will open a PR from `feature/skeleton-initial` into `main` with this initial skeleton and create GitHub issues for the remaining items.
