import '../core/model/searchWord.dart';
import '../core/model/testKanji.dart';
import '../core/view_mode/likewordModel.dart';
import '../core/view_mode/signupModel.dart';
import '../core/view_mode/testModel.dart';
import '../core/view_mode/wordModel.dart';
import '../helper/generalinfor.dart';
import '../ui/TestPage.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:provider/provider.dart';

import 'WordPage.dart';

class LikeWordPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return new _LikeWordPage();
  }
}

class _LikeWordPage extends State<LikeWordPage> {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Consumer<LikeWordModel>(builder: (_ ,model, __){
      return Scaffold(
        appBar: AppBar(
//        leading: buildPreviousButton(),
          backgroundColor: colorApp,
          title: Text(
            'Từ vựng yêu thích',
          ),

          centerTitle: true,
        ),
        body: Center(
          child: FutureBuilder<List<SearchWord>>(
              future: model.likeWord(),
              builder: (context, snapshot) {
                if(snapshot.hasData){
                  return ListView.builder(
                      itemCount: snapshot.data.length,
                      itemBuilder: (BuildContext context, int index) {
                        List<SearchWord> likeWord = snapshot.data;
                        return Card(
                          margin: EdgeInsets.only(left: 10.0, right: 10.0, top: 10.0),
                          child: Container(
                            child: ListTile(
                              leading: Text(
                                '${likeWord[index].kanji}',
                                style: TextStyle(fontSize: 35),
                              ),
//                              trailing: IconButton(
//                                  icon: model.Likeword
//                                      ? Icon(
//                                    Icons.favorite,
//                                    color: Colors.pink,
//                                  )
//                                      : Icon(Icons.favorite),
//                                  onPressed: () {
//                                    model.change();
//                                  }),
                              title: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: <Widget>[
                                  Text(
                                    (likeWord[index].kunyomi != null) ? 'kun: ${likeWord[index].kunyomi}' :"kun: " ,
                                    style: TextStyle(color: Colors.purple),
                                  ),
                                  Text((likeWord[index].onyomi != null) ? 'on: ${likeWord[index].onyomi}' :"on: ", style: TextStyle(color: Colors.red)),
                                ],
                              ),
                              onTap: () {
//                                print(likeWord[index].idKanji);
                                Provider.of<WordModel>(context).getDataKunYomi(likeWord[index].idKanji);
                                Provider.of<WordModel>(context).setLikeword(likeWord[index].status);

                                Navigator.push(
                                    context,
                                    MaterialPageRoute(
                                        builder: (context) => WordPage(likeWord[index])));
                              },
                            ),
                          ),
                        );
                      });
                }
                return Center(
                  child: Text(
                    'Trống !!!',
                    style: TextStyle(fontSize: 30, color: Colors.grey),
                  ),
                );
              }
          ),
        ),
        bottomNavigationBar: Consumer<TestModel>(builder:(_ ,model, __){
          return Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              RaisedButton(
                color: colorApp,
                onPressed: () {
                  model.getDataTestPage('meaning');
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                      builder: (context) => TestPage()));
                },
                child: Text(
                  'Kiểm tra nghĩa',
                  style: TextStyle(fontSize: 20, color: Colors.white),
                ),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(60),
                ),
              ),
              SizedBox(width: 40.0,),
              RaisedButton(
                color: colorApp,
                onPressed: () {
                  model.getDataTestPage('yomikata');
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => TestPage()));
                },
                child: Text(
                  'Kiểm tra cách đọc',
                  style: TextStyle(fontSize: 20, color: Colors.white),
                ),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(60),
                ),
              ),
            ],
          );
        })

      );
    });
  }

}