import 'package:demoappkanji/core/model/testKanji.dart';
import 'package:demoappkanji/core/view_mode/testModel.dart';
import 'package:demoappkanji/helper/generalinfor.dart';
import 'package:demoappkanji/ui/HomePage.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class Result extends StatefulWidget {
List<TestKanji> question;
Result(this.question);
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _Result_State(this.question);
  }
}

class _Result_State extends State<Result> {
  List<TestKanji> question;
  _Result_State(this.question);
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return WillPopScope(
      onWillPop:() async => false,
        child: Scaffold(
          appBar: AppBar(
            automaticallyImplyLeading: false,
//        leading: buildPreviousButton(),
            backgroundColor: colorApp,
            title: Text(
              'Kết Quả',
            ),

            centerTitle: true,
          ),
          body: Consumer<TestModel>(builder: (_ ,model, __){
            return SingleChildScrollView(
              child: Center(
                child: model.isFetchingResult ?
                Column(
                  children: <Widget>[
                    SizedBox(height: 40,),
//                    Wrap(
//                      direction: Axis.horizontal,
//                      spacing: 20.0,
//                      runSpacing: 20.0,
//                      children: [
//                        ...List.generate(question.length, (index) {
//                          return _buildBox(question[index], index, model.resultData.results[index]);
//                        }),
//                      ],
//                    ),
//                    SizedBox(
//                      height: 20.0,
//                    ),


                    ...List.generate(model.question.length, (index) {
                      return _questionBox(model.question[index], index, model.group[index], model.resultData.results[index]);
                    }),
                    SizedBox(
                      height: 20.0,
                    ),
                    Text(
                      'Số câu trả lời đúng: ${model.resultData.correct_answers}/10',
                      style: TextStyle(
                        color: Colors.grey,
                        fontSize: 25,
                      ),
                    ),


                    RaisedButton(
                      color: colorApp,
                      onPressed: () {
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => HomePage()));

                      },
                      child: Text(
                        'Trang chủ',
                        style: TextStyle(fontSize: 20, color: Colors.white),
                      ),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(60),
                      ),
                    ),
                  ],
                )
                    :
                CircularProgressIndicator(),
              ),
            );
          }),
        ), );
  }

  Widget _buildBox(TestKanji _question, int index, String val) {
    return Container(
      color: (val == 'true')? Colors.green:Colors.red,
      width: 60,
      height: 60,
      child: Center(
        child: Text(
          '${index+1}.${_question.answer}',
          style: TextStyle(color: Colors.white),
        ),
      ),
    );
  }

  Widget _questionBox(TestKanji _question, int index, int group, String val) {
    return Consumer<TestModel>(builder:(_ , model, __){
      return
//        Padding(
//        padding: EdgeInsets.all(0),
//        child:
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,

          children: <Widget>[
            Container(
              width: double.infinity,
              color: (val == 'true')? Colors.green:Colors.red,

              child: Text(
                'Câu ${index+1} : ${_question.question}',
                style: TextStyle( fontSize: 30),
              ),
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
//                    onChanged: (val) {
//                      model.setGroup(index, val);
//                    },
                  ),
                  RadioListTile(
                    title: Text(
                      'B. ${_question.answerops.B_option}',
                      style: TextStyle(color: Colors.black45, fontSize: 20),
                    ),
                    value: 1,
                    groupValue: model.group[index],
//                    onChanged: (val) {
//                      model.setGroup(index, val);
//
//
//                    },
                  ),
                  RadioListTile(
                    title: Text(
                      'C. ${_question.answerops.C_option}',
                      style: TextStyle(color: Colors.black45, fontSize: 20),
                    ),
                    value: 2,
                    groupValue: model.group[index],
//                    onChanged: (val) {
//                      model.setGroup(index, val);
//
//
//                    },
                  ),
                  RadioListTile(
                    title: Text(
                      'D. ${_question.answerops.D_option}',
                      style: TextStyle(color: Colors.black45, fontSize: 20),
                    ),
                    value: 3,
                    groupValue: model.group[index],
//                    onChanged: (val) {
//                      model.setGroup(index, val);
//
//
//                    },
                  ),

                ],
              ),
            ),
            Text(
              'Đáp án : ${_question.answer}',
              style: TextStyle( fontSize: 30),
            ),
            SizedBox(height: 15,)
          ],
        );
//      );
    });
  }
}
