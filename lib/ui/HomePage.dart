import 'package:demoappkanji/core/model/searchWord.dart';
import 'package:demoappkanji/core/view_mode/homeModel.dart';
import 'package:demoappkanji/core/view_mode/wordModel.dart';
import 'package:demoappkanji/helper/generalinfor.dart';
import 'package:demoappkanji/helper/screenConfig.dart';
import 'package:demoappkanji/ui/LikeWordPage.dart';
import 'package:demoappkanji/ui/LoginPage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'WordPage.dart';

class HomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _HomePage_State();
  }
}

class _HomePage_State extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    GlobalKey<FormState> _key1 = new GlobalKey();
    bool _validate = false;
    TextEditingController _searchWord = new TextEditingController();

    String validateText(String value) {
      if (value.length == 0) {
        return null;
      } else {
        return null;
      }
    }

    _saveToServer() {
      if (_key1.currentState.validate()) {
        // No any error in validation
        _key1.currentState.save();
      } else {
        // validation error
        setState(() {
          _validate = true;
        });
      }
    }

    SizeConfig().init(context);
    return Scaffold(
      floatingActionButton: new FloatingActionButton(
        backgroundColor: colorApp,
        child: Icon(
          Icons.favorite,
          color: Colors.red,
          size: 35,
        ),
        onPressed: () {
          Navigator.push(
              context, MaterialPageRoute(builder: (context) => LikeWordPage()));
        },
      ),
      body: Consumer<HomeModel>(builder: (_, model, __) {
        return NestedScrollView(
            headerSliverBuilder:
                (BuildContext context, bool innerBoxIsScrolled) {
              return <Widget>[
                SliverAppBar(
                  automaticallyImplyLeading: false,
                  backgroundColor: colorApp,
                  expandedHeight: 40,
//              floating: false,
                  pinned: true,
                  flexibleSpace: AppBar(
                    automaticallyImplyLeading: false,
                    actions: <Widget>[
                      IconButton(
                          icon: Icon(
                            Icons.exit_to_app,
                            size: 35,
                          ),
                          onPressed: () {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => LoginPage()));
                          }),
                    ],
                    backgroundColor: colorApp,
                    title: Text(
                      'Tra cứu',
                    ),
                    centerTitle: true,
                  ),
                ),
                SliverPersistentHeader(
                  pinned: true,
                  delegate: _SliverAppBarDelegate(
                      child: PreferredSize(
                    preferredSize: Size.fromHeight(65),
                    child: Container(
                      color: Colors.white,
                      padding: EdgeInsets.only(right: 10.0, left: 10.0),
                      height: SizeConfig.safeBlockVertical * 15,
//            child: Card(

                      child: Form(
                          key: _key1,
                          autovalidate: _validate,
                          child: TextFormField(
                            textAlign: TextAlign.start,
                            style: TextStyle(fontSize: 25.0),
                            keyboardType: TextInputType.text,
//                            maxLength: 10,
                            validator: validateText,
                            onSaved: (String val) {
                              _searchWord.text = val.trim();
                            },
                            decoration: InputDecoration(
                                icon: Icon(
                                  Icons.collections,
                                  size: 45,
                                  color: colorApp,
                                ),
                                hintText: '終，chung',
                                border: InputBorder.none,
                                hintStyle: TextStyle(
                                    color: Colors.grey,
                                    fontSize: 25,
                                    fontStyle: FontStyle.normal),
                                suffixIcon: IconButton(

                                    icon: Icon(
                                      Icons.search,
                                      color: colorApp,
                                      size: 27,
                                    ),
                                    onPressed: () {
                                      _saveToServer();
                                      if (_searchWord.text.isNotEmpty) {
                                        model.getDataWord(_searchWord.text);
                                      }
                                    })),
                          )),
                    ),
                  )),
                )
              ];
            },
            body: model.checkData && model.word != null
                ? ListView.builder(
                    itemCount: model.wordLength,
                    itemBuilder: (BuildContext context, int index) {
                      List<SearchWord> words = model.word;
//                      print(words[index].status);
//                      print(words.toString());
                      return Card(
                        margin:
                            EdgeInsets.only(left: 10.0, right: 10.0, top: 10.0),
                        child: Container(
//                  padding: EdgeInsets.all(5.0),
//                        height: SizeConfig.blockSizeVertical*10,
                          child: ListTile(
                            leading: Text(
                              '${words[index].kanji}',
                              style: TextStyle(fontSize: 35),
                            ),
//                            trailing: IconButton(
//                                icon: model.Likeword
//                                    ? Icon(
//                                        Icons.favorite,
//                                        color: Colors.pink,
//                                      )
//                                    : Icon(Icons.favorite),
//                                onPressed: () {
//                                  model.change();
//                                }),
                            title: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: <Widget>[
                                Text(
                                  (words[index].kunyomi != null) ? 'kun: ${words[index].kunyomi}' :"kun: ",
                                  style: TextStyle(color: Colors.purple),
                                ),
                                Text((words[index].onyomi != null) ? 'on: ${words[index].onyomi}' :"on: ",
                                    style: TextStyle(color: Colors.red)),
                              ],
                            ),
                            onTap: () {
                              Provider.of<WordModel>(context).getDataKunYomi(words[index].idKanji);
                              Provider.of<WordModel>(context).likeWord(words[index].idKanji);

                              Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => WordPage(words[index])));
                            },
                          ),
                        ),
                      );
                    })
                : Center(
                    child: Text(
                      'Không tìm thấy !!!',
                      style: TextStyle(fontSize: 30, color: Colors.grey),
                    ),
                  )
//        body: FutureBuilder<List<SearchWord>>(
//          future: model.search(),
//            builder: null
//        ),
        );
      }),
    );
  }
}

class _SliverAppBarDelegate extends SliverPersistentHeaderDelegate {
  final PreferredSize child;

  _SliverAppBarDelegate({this.child});

  @override
  Widget build(
      BuildContext context, double shrinkOffset, bool overlapsContent) {
    // TODO: implement build
    return child;
  }

  @override
  // TODO: implement maxExtent
  double get maxExtent => child.preferredSize.height;

  @override
  // TODO: implement minExtent
  double get minExtent => child.preferredSize.height;

  @override
  bool shouldRebuild(SliverPersistentHeaderDelegate oldDelegate) {
    // TODO: implement shouldRebuild
    return false;
  }
}
