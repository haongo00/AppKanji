import '../core/model/Yomi.dart';
import '../core/model/searchWord.dart';
import '../core/view_mode/wordModel.dart';
import '../helper/generalinfor.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'LikeWordPage.dart';

class WordPage extends StatefulWidget {
  SearchWord word;

  WordPage(this.word);

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _WordPage_State(word);
  }
}

class _WordPage_State extends State<WordPage> {
  SearchWord word;

  _WordPage_State(this.word);

  final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();

  void showInSnackBar(String value) {
    _scaffoldKey.currentState.showSnackBar(
      SnackBar(
        content: new Text(value, textAlign: TextAlign.start),
        duration: Duration(seconds: 1),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Consumer<WordModel>(builder: (_, model, __) {
//      model.setLikeword(word.status);
      return Scaffold(
        key: _scaffoldKey,
        floatingActionButton: new FloatingActionButton(
          backgroundColor: colorApp,
          child: Icon(
            Icons.favorite,
            color: Colors.red,
            size: 35,
          ),
          onPressed: () {
//                print(word.idKanji);
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => LikeWordPage()));
          },
        ),
        appBar: AppBar(
//          automaticallyImplyLeading: false,
          backgroundColor: colorApp,
          title: Text(
            '${word.kanji}',
            style: TextStyle(fontSize: 35),
          ),
          centerTitle: true,
        ),
        body: ListView(
          children: <Widget>[
            Card(
              child: Column(
                children: <Widget>[
                  IconButton(
                      icon: model.Likeword
                          ? Icon(
                              Icons.favorite,
                              color: Colors.pink,
                            )
                          : Icon(
                              Icons.favorite,
                              color: Colors.grey,
                            ),
                      onPressed: () async {
                        print('log1' + model.Likeword.toString());
                        if (model.Likeword) {
                          model.deleteLikeWord(word.idKanji);
                          model.setMessage('Xóa từ thành công !!!');

//                          print('delete like');

                        } else {
                          model.likeWord(word.idKanji);
                          model.setMessage('Thêm từ thành công !!!');

//                          print('like');

                        }

                        ;
                        model.change();
                        print('log2' + model.Likeword.toString());

                        var _message = await model.message;
                        showInSnackBar(_message);
                      }),
                  _line('Kanji:', '${word.kanji} - ${word.hanviet}'),
                  _lineColor('Kunyomi:', Colors.purple,
                      (word.kunyomi != null) ? '${word.kunyomi}' : ''),
                  _lineColor('Onyomi:', Colors.red,
                      (word.onyomi != null) ? '${word.onyomi}' : ''),
                  _line('Số nét:',
                      (word.strokes != null) ? '${word.strokes}' : ''),
                  _line('JLPT:', (word.jlpt != null) ? '${word.jlpt}' : ''),
                  _line('Bộ:', (word.set != null) ? '${word.set}' : ''),
                  _line('Nghĩa:',
                      (word.meaning != null) ? '${word.meaning}' : ''),
//                  IconButton(
//                      icon: Icon(
//                        Icons.keyboard_arrow_down,
//                        size: 40,
//                      ),
//                      onPressed: () {
//                        model.getDataKunYomi(word.idKanji);
//                      })
                ],
              ),
            ),
            SizedBox(
              height: 15,
            ),
            Card(
              child: Padding(
                padding: EdgeInsets.all(10),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      'Ví dụ phân loại theo cách đọc:',
                      style:
                          TextStyle(fontSize: 20, fontWeight: FontWeight.w700),
                    ),
                    model.isFetchYomi ? _tableKun(model.kun) : SizedBox(),
                    model.isFetchYomi ? _tableOn(model.on) : SizedBox(),
                    SizedBox(
                      height: 15,
                    )
                  ],
                ),
              ),
            ),
          ],
        ),
      );
    });
  }

  Widget _line(String _tieude, String _thongtin) {
    return Padding(
      padding: EdgeInsets.only(left: 10, right: 10, bottom: 15),
      child: Row(
        children: <Widget>[
          Container(
//          alignment: Alignment.center,
            padding: EdgeInsets.only(left: 5.0, bottom: 5.0),
            width: 85,
            color: Colors.grey[400],
            child: Text(
              _tieude,
              style: TextStyle(fontSize: 20),
            ),
          ),
          SizedBox(
            width: 10.0,
          ),
          Expanded(
            child: Text(
              _thongtin,
              style: TextStyle(fontSize: 20),
            ),
          )
        ],
      ),
    );
  }

  Widget _lineColor(
    String _tieude,
    Color _color,
    String _thongtin,
  ) {
    return Padding(
      padding: EdgeInsets.only(left: 10, right: 10, bottom: 15),
      child: Row(
        children: <Widget>[
          Container(
//          alignment: Alignment.center,
            padding: EdgeInsets.only(left: 5.0, bottom: 5.0),
            width: 85,
            color: Colors.grey[400],
            child: Text(
              _tieude,
              style: TextStyle(fontSize: 20),
            ),
          ),
          SizedBox(
            width: 10.0,
          ),
          Expanded(
            child: Text(
              _thongtin,
              style: TextStyle(fontSize: 20, color: _color),
            ),
          )
        ],
      ),
    );
  }

  Widget _tableKun(List<Yomi> kun) {
    return Column(
      children: <Widget>[
        SizedBox(
          height: 20,
        ),
        Container(
          padding: EdgeInsets.all(5.0),
          width: double.infinity,
          color: Colors.purple[200],
          child: Text(
            'Kunyomi',
            style: TextStyle(
              fontSize: 18,
            ),
          ),
        ),
        SizedBox(
          height: 10.0,
        ),
        Container(
          child: Table(
            border: TableBorder.all(color: Colors.grey),
            children: [
              ...List.generate(kun.length, (index) {
                return TableRow(children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      _text_table(kun[index].vocab != null
                          ? '${kun[index].vocab}'
                          : ''),
                      _text_table(kun[index].yomikata != null
                          ? '${kun[index].yomikata}'
                          : ''),
                    ],
                  ),
                  _text_table(kun[index].hanviet != null
                      ? '${kun[index].hanviet}'
                      : ''),
                  _text_table(kun[index].meaning != null
                      ? '${kun[index].meaning}'
                      : ''),
                ]);
              }),
            ],
          ),
        ),
      ],
    );
  }

  Widget _tableOn(List<Yomi> on) {
    return Column(
      children: <Widget>[
        SizedBox(
          height: 20,
        ),
        Container(
          padding: EdgeInsets.all(5.0),
          width: double.infinity,
          color: Colors.red[200],
          child: Text(
            'Onyomi',
            style: TextStyle(
              fontSize: 18,
            ),
          ),
        ),
        SizedBox(
          height: 10.0,
        ),
        Container(
          child: Table(
            border: TableBorder.all(color: Colors.grey),
            children: [
              ...List.generate(on.length, (index) {
                return TableRow(children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      _text_table(
                          on[index].vocab != null ? '${on[index].vocab}' : ''),
                      _text_table(on[index].yomikata != null
                          ? '${on[index].yomikata}'
                          : ''),
                    ],
                  ),
                  _text_table(
                      on[index].hanviet != null ? '${on[index].hanviet}' : ''),
                  _text_table(
                      on[index].meaning != null ? '${on[index].meaning}' : ''),
                ]);
              }),
            ],
          ),
        ),
      ],
    );
  }

  Widget _text_table(String _text) {
    return Padding(
      padding: EdgeInsets.only(left: 5.0),
      child: Text(_text,
          style: TextStyle(
            fontSize: 18,
          )),
    );
  }
}
