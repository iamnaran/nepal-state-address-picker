# Nepal State Address Picker Android Library

The Nepal State Address Picker is a utility library for Android applications, written in Java, that allows users to select states, municipalities, and their districts from a bottom sheet dialog. It provides a simple and intuitive way to integrate address selection functionality into your Android projects.

## Installation

### Step 1: Add the JitPack repository to your build file

Add the following code snippet to your root `build.gradle` file, at the end of the `repositories` block:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Step 2: Add the dependency
Add the following dependency to your app module's build.gradle file:

```gradle
dependencies {
    implementation 'com.github.iamnaran:nepal-state-address-picker:Tag'
}
```
Replace Tag with the desired version of the library.

### Usage
In your Android project, you can use the Nepal State Address Picker by following these steps:
- Add a button or any other UI element to trigger the address picker dialog.
- Set an OnClickListener for the button and open the address picker dialog.

```java
btnPicker.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        NepalAddressPicker.openNepalAddressPickerDialog(MainActivity.this, new AddressPickerListener() {
            @Override
            public void onAddressSelected(SelectedNepalAddress selectedNepalAddress) {
                SelectedNepalAddress data = selectedNepalAddress;
                Log.e("TAG", "onAddressSelected: "+ data.getProvince().getName());
                Log.e("TAG", "onAddressSelected: "+ data.getDistrict().getName());
                Log.e("TAG", "onAddressSelected: "+ data.getMunicipality().getName());
            }
        });
    }
});
```
This code snippet demonstrates how to handle the selection event and retrieve the selected address components such as province, district, and municipality.

Feel free to customize the UI and integrate the address picker according to your app's requirements.