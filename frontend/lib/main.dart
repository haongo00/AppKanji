
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'provider_setup.dart' as ProviderSetup;
import 'ui/LoginPage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
        providers: ProviderSetup.getProviders(),
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          title: 'Kanji',
          theme: ThemeData(fontFamily: 'UTM'),
//          initialRoute: '/',
//          routes: {
//            '/': (context) => LoginPage(),
//            '/signup': (context) => SignUpPage(),
//          },
          home: LoginPage(),
        ));
  }
}


