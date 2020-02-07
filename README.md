# Imeja Dialog

:fire: Display a progress dialog with customized options :fire:

![gif](https://media.giphy.com/media/H5GZlMJcqQtnJMZn5i/giphy.gif)

## Add dependency

Add this in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}		
```
Add this in your app level gradle:

```

    implementation 'com.github.Keeprawteach:Imeja-Dialog:1.0.0'
```

## Initialization
 
Initialize in the Activity file:

```
ImejaDialog dialog
 
```

## Customization

Customize according to your need.

```

        dialog = new ImejaDialog.Builder(ImejaDialogActivity.this)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                })

                .setSpinnerColorRes(R.color.colorGreen)
                .setMessageColorRes(R.color.colorAccent)
                .setTitle(R.string.standard_title)
                .setTitleColorRes(R.color.colorPrimary)
                .setMessageContent(getString(R.string.standard_message))
                .setCancelable(true)
                .setMessageContentGravity(Gravity.END)
                .build();
```
***Show the dialog on any click action***
```
dialog.show();
```

## Contributing :heart_eyes:
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

```
MIT License

Copyright (c) 2020 Kiprotich Japheth

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
