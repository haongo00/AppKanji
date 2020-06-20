import '../core/model/testKanji.dart';
import '../core/view_mode/testModel.dart';
import '../helper/generalinfor.dart';
import '../ui/ResultPage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class TestPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _TestPage_State();
  }
}

class _TestPage_State extends State<TestPage> {

  int group1 = 0;

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Consumer<TestModel>(builder: (_, model, __){
      return Scaffold(
        appBar: AppBar(
//        leading: buildPreviousButton(),
          backgroundColor: colorApp,
          title: Text(
            'Test Kanji',
          ),

          centerTitle: true,
        ),
        body: model.isFetchingDataTestPage ?
        SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Center(
                child: Text(
                  'Chọn câu trả lời đúng nhất*',
                  style: TextStyle(color: Colors.red, fontSize: 25),
                ),
              ),
              ...List.generate(model.question.length, (index) {
                return _questionBox(model.question[index], index, model.group[index]);
              }),
            ],
          ),
        )
        : Center(
          child: CircularProgressIndicator(),
        ),
        bottomNavigationBar: RaisedButton(
            child: Text('Nộp Bài'),
            onPressed: (){
              print(model.group.toString());
            String answerToServer = model.getAnswer();
            print(answerToServer);
              model.getResultData(answerToServer);
              Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => Result(model.question)));
            }
        ),
      );
    });
  }

  Widget _questionBox(TestKanji _question, int index, int group) {
    return Consumer<TestModel>(builder:(_ , model, __){
      return Padding(
        padding: EdgeInsets.all(15.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,

          children: <Widget>[
            Text(
              'Câu ${index+1} : ${_question.question}',
              style: TextStyle( fontSize: 30),
            ),
            Center(
              child: Column(
                children: <Widget>[
                  RadioListTile(
                    title: Text(
                      'A. ${_question.answerops.A_option}',

                      style: TextStyle(color: Colors.black45, fontSize: 20),
                    ),
                    value: 0,
                    groupValue:model.group[index],
                    onChanged: (val) {
                      model.setGroup(index, val);
                    },
                  ),
                  RadioListTile(
                    title: Text(
                      'B. ${_question.answerops.B_option}',
                      style: TextStyle(color: Colors.black45, fontSize: 20),
                    ),
                    value: 1,
                    groupValue: model.group[index],
                    onChanged: (val) {
                      model.setGroup(index, val);


                    },
                  ),
                  RadioListTile(
                    title: Text(
                      'C. ${_question.answerops.C_option}',
                      style: TextStyle(color: Colors.black45, fontSize: 20),
                    ),
                    value: 2,
                    groupValue: model.group[index],
                    onChanged: (val) {
                      model.setGroup(index, val);


                    },
                  ),
                  RadioListTile(
                    title: Text(
                      'D. ${_question.answerops.D_option}',
                      style: TextStyle(color: Colors.black45, fontSize: 20),
                    ),
                    value: 3,
                    groupValue: model.group[index],
                    onChanged: (val) {
                      model.setGroup(index, val);


                    },
                  ),
                ],
              ),
            ),
          ],
        ),
      );
    });
  }
}
