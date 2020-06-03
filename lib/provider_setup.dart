import 'package:demoappkanji/core/view_mode/signupModel.dart';
import 'package:demoappkanji/core/view_mode/testModel.dart';
import 'package:provider/provider.dart';

import 'core/authenticationservice.dart';
import 'core/view_mode/homeModel.dart';
import 'core/view_mode/likewordModel.dart';
import 'core/view_mode/loginModel.dart';
import 'core/view_mode/wordModel.dart';

List<SingleChildCloneableWidget> getProviders() {
  List<SingleChildCloneableWidget> independentServices = [
    ChangeNotifierProvider(
      create: (context) => AuthenticationService(),
    ),
  ];

  List<SingleChildCloneableWidget> dependentServices = [
    ChangeNotifierProxyProvider<AuthenticationService, LoginModel>(
      update: (_, authenticationService, __) =>
          LoginModel(authenticationService: authenticationService),
    ),
    ChangeNotifierProxyProvider<AuthenticationService, SignUpModel>(
      update: (_, authenticationService, __) =>
          SignUpModel(authenticationService: authenticationService),
    ),
    ChangeNotifierProxyProvider<AuthenticationService, HomeModel>(
      update: (_, authenticationService, __) =>
          HomeModel(authenticationService: authenticationService),
    ),
    ChangeNotifierProxyProvider<AuthenticationService, WordModel>(
      update: (_, authenticationService, __) =>
          WordModel(authenticationService: authenticationService),
    ),
    ChangeNotifierProxyProvider<AuthenticationService, LikeWordModel>(
      update: (_, authenticationService, __) =>
          LikeWordModel(authenticationService: authenticationService),
    ),
    ChangeNotifierProxyProvider<AuthenticationService, WordModel>(
      update: (_, authenticationService, __) =>
          WordModel(authenticationService: authenticationService),
    ),
    ChangeNotifierProxyProvider<AuthenticationService, TestModel>(
      update: (_, authenticationService, __) =>
          TestModel(authenticationService: authenticationService),
    ),
  ];

  List<SingleChildCloneableWidget> providers = [
    ...independentServices,
    ...dependentServices,
//     ...uiConsumableProviders
  ];

  return providers;
}
