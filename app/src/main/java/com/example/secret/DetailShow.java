package com.example.secret;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

class Secret<String> {
    String sec;
    Secret<String> birth;
    Secret<String> hurt;
    public java.lang.String palace;
    public java.lang.String animal;
    public int branch_num;
    public ArrayList<String> branch;
    boolean iscontains(Branch goal){
        for(int i = 0; i < branch_num; i++){
            if(branch.get(i) == goal.bra)
                return true;
        }
        return false;
    };
}

class Branch<String>{
    String bra;
    Branch next;
}

class Animals<String>{
    String animal;
    Secret sec;
}


class Group{

    public int id;
    public String animal;
    public String star;
    public Branch branch;
    public String palace;
    public String A;
    public String B;
    public String C;

}

public class DetailShow extends AppCompatActivity {

    String animals[ ] = {"青龙","白虎","朱雀","玄武","腾蛇","勾陈"};
    String secr[] = {"金", "水", "木", "火", "土"};
    String palas[] = {"大安", "留连", "速喜", "赤口", "小吉", "空亡"};
    String star[] = {"金星", "水星", "木星", "火星", "土星"};

    String branch1[] = {"子", "寅", "辰", "午", "申", "戌"};
    String branch2[] = {"丑", "卯", "巳", "未", "酉", "亥"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_show);

        Toast toast;
        int i;

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        Boolean sex = bundle.getBoolean("sex");
        int month = bundle.getInt("month");
        int day = bundle.getInt("day");
        int hour = bundle.getInt("hour");

        Group content[] = new Group[6];
        for( i =0; i < 6; i++){
            content[i] = new Group();
            content[i].branch = new Branch();
            content[i].id = i;
        }

        Secret s[] = new Secret[5];
        for(i = 0; i < 5; i ++) {
            s[i] = new Secret();
            s[i].birth = new Secret();
            s[i].hurt = new Secret();
            s[i].branch = new ArrayList();
        }

        for(i = 0; i < 5; i ++) {
            s[i].sec = secr[i];
            //s[i].birth.sec = secr[(i + 1) % 5];
            //s[i].hurt.sec = secr[(i + 2) % 5];

            if(s[i].sec == secr[0])
            {
                s[i].branch_num = 2;
                s[i].branch.add(branch1[4]);
                s[i].branch.add(branch2[4]);
                s[i].animal = animals[1];
                s[i].palace = palas[3];
            }

            else if(s[i].sec == secr[1])
            {
                s[i].branch_num = 2;
                s[i].branch.add(branch1[0]);
                s[i].branch.add(branch2[5]);
                s[i].animal = animals[3];
                s[i].palace = palas[4];
            }

            else if(s[i].sec == secr[2])
            {
                s[i].branch_num = 2;
                s[i].branch.add(branch1[1]);
                s[i].branch.add(branch2[1]);
                s[i].animal = animals[0];
                s[i].palace = palas[0];
            }

            else if(s[i].sec == secr[3])
            {
                s[i].branch_num = 2;
                s[i].branch.add(branch1[3]);
                s[i].branch.add(branch2[2]);
                s[i].animal = animals[2];
                s[i].palace = palas[1];
            }

            else if(s[i].sec == secr[4])
            {
                s[i].branch_num = 4;
                s[i].branch.add(branch1[2]);
                s[i].branch.add(branch1[5]);
                s[i].branch.add(branch2[0]);
                s[i].branch.add(branch2[3]);
                s[i].animal = animals[4];
                s[i].palace = palas[2];
            }

            else
            {
                toast = Toast.makeText(getApplicationContext(),"五行错误了",Toast.LENGTH_LONG);
            }
        }
        for(i = 0; i < 5; i ++) {
            s[i].birth = s[(i+1)%5];
            s[i].hurt = s[(i+2)%5];
        }

        Branch b[] = new Branch[6];
        if((hour %2) == 1)
        {
            for( i = 0; i < 6; i++)
            {
                b[i] = new Branch();
                b[i].next = new Branch();
                b[i].bra = branch2[i];
                b[i].next.bra = branch2[(i+1)%6];
            }
        }
        else
        {
            for( i = 0; i < 6; i++)
            {
                b[i] = new Branch();
                b[i].next = new Branch();
                b[i].bra = branch1[i];
                b[i].next.bra = branch1[(i+1)%6];
            }
        }

        int self = month + day + hour;
        int base = self % 6;
        content[base].B = "自身";
        content[base].branch = b[hour/2];
        for(i = 0; i < 6; i++) {
            content[(base + i) % 6].branch.bra = b[(hour / 2 + i) % 6].bra;
            for (int j = 0; j < 5; j++) {
                if(content[(base + i) % 6].branch.bra == "辰" || content[(base + i) % 6].branch.bra == "丑"){
                    content[(base + i) % 6].animal = "勾陈";
                    content[(base + i) % 6].palace = palas[(base + i) % 6];
                    continue;
                }
                if (s[j].branch.contains(content[(base + i) % 6].branch.bra) && (content[(base + i) % 6].branch.bra != "辰" || content[(base + i) % 6].branch.bra != "丑")) {
                    content[(base + i) % 6].animal = s[j].animal;
                    content[(base + i) % 6].palace = palas[(base + i) % 6];
                }
            }
        }

//        for(i = 0; i < 6; i++){
//            if(content[i].palace.isEmpty()){
//                content[i].palace = palas[5];
//            }
//        }

        int start = (month + day) % 6;
        //content[start].star = star[2];
        for(i = 0; i < 5; i++)
        {
            content[ (start + i) % 6].star = star[(2+i) % 5];
        }
        content[ (start + i) % 6].star ="天空";

        Secret center = new Secret();
        for(i = 0; i < 5; i++){
            if(s[i].branch.contains(content[base].branch.bra)){
                //center.sec = s[i].sec;
                center = s[i];
                center.hurt = s[i].hurt;
                center.birth = s[i].birth;
            }
        }

        for(i = 0; i < 5 ; i++){
            //生我
            if(s[i].birth.sec == center.sec){
                for(int j = 0; j < 6; j++) {
                    if (content[j].star == star[i]) {
                        content[j].A = "父亲";
                    }
                    if(content[j].palace == s[i].palace){
                        content[j].C = "母亲";
                    }
                    if(s[i].branch.contains(content[j].branch.bra) && center.sec != "金"){
                        content[j].B = "贵人";
                    }
                }
            }
            //同我
            if(s[i].sec == center.sec){
                for(int j = 0; j < 6; j++) {
                    if (content[j].star == star[i]) {
                        content[j].A = "兄弟";
                    }
                    if(content[j].palace == s[i].palace){
                        content[j].C = "姐妹";
                    }
                }
            }

            //生星
            if(center.birth.sec == s[i].sec) {
                for (int j = 0; j < 6; j++) {
                    if (content[j].star == star[i]) {
                        content[j].A = "儿子";
                    }
                }
            }

            //克星
            if(center.hurt.sec == s[i].sec) {
                for (int j = 0; j < 6; j++) {
                    if (content[j].star == star[i]) {
                        content[j].A = "偏财";
                    }
                }
            }

            //星克
            if(s[i].hurt.sec == center.sec){
                for (int j = 0; j < 6; j++) {
                    if (content[j].star == star[i]) {
                        content[j].A = "偏职";
                    }
                    if(content[j].palace == s[i].palace){
                        content[j].C = "正职";
                    }
                }
            }


        }

        //克我和我克支
        if(sex)
        {
            //if(center.hurt.sec == s[i].sec){
            for(i=0; i<5; i++)
            {
                if(s[i].hurt.equals(center) && i != 2){
                    break;
                }
            }
             for(int j = 0; j < 6; j++) {
                    if(center.hurt.branch.contains(content[j].branch.bra) && center.sec != "木"){
                        content[j].B = "妻子";
                    }
                    if(i < 5) {
                        if (s[i].branch.contains(content[j].branch.bra)) {
                            content[j].B = "小人";
                        }
                    }
                }
            //}
        }else {
           // if(s[i].hurt.sec == center.sec){
                for(i=0; i < 5; i++)
                {
                    if(s[i].hurt.equals(center) && i != 2){
                        break;
                    }
                }
                for(int j = 0; j < 6; j++) {
                    if(i < 5) {
                        if (s[i].branch.contains(content[j].branch.bra)) {
                            content[j].B = "丈夫";
                        }
                    }
                    if(center.hurt.branch.contains(content[j].branch.bra)){
                        content[j].B = "小人";
                    }
                }
           // }
        }

        //生宫
       // if(center.birth.palace == s[i].palace){
        for (int j = 0; j < 6; j++){
                if(content[j].palace == center.birth.palace){
                    content[j].C = "女儿";
                }
                if(center.hurt.palace == content[j].palace){
                    content[j].C = "正财";
                }
                if(center.birth.branch.contains(content[j].branch.bra) && center.sec != "火"){
                    content[j].B = "疾病";
                }


            }
        if(center.sec == "金"){
                if(hour % 2 == 0){
                    for(int j =0; j < 6 ; j++){
                        if(content[j].branch.bra == "戌"){
                            content[j].B = "朋友";
                        }
                        if(content[j].branch.bra == "辰"){
                            content[j].B = "贵人";
                        }
                    }
                }else {
                    for(int j =0; j < 6 ; j++){
                        if(content[j].branch.bra == "丑"){
                            content[j].B = "朋友";
                        }
                        if(content[j].branch.bra == "未"){
                            content[j].B = "贵人";
                        }
                    }
                }

        }else if(center.sec == "水"){
                if(hour % 2 == 0){
                    for(int j =0; j < 6 ; j++){
                        if(content[j].branch.bra == "戌"){
                            if(sex)
                                content[j].B = "小人";
                            else
                                content[j].B ="丈夫";
                        }
                        if(content[j].branch.bra == "辰"){
                            content[j].B = "朋友";
                        }
                    }
                }else {
                    for(int j =0; j < 6 ; j++){
                        if(content[j].branch.bra == "丑"){
                            content[j].B = "朋友";
                        }
                        if(content[j].branch.bra == "未"){
                            if(sex)
                                content[j].B = "小人";
                            else
                                content[j].B ="丈夫";
                        }
                    }
                }
        } else if(center.sec == "木"){
            if(hour % 2 == 0){
                for(int j =0; j < 6 ; j++){
                    if(content[j].branch.bra == "戌"){
                        if(sex)
                            content[j].B = "妻子";
                        else
                            content[j].B ="小人";
                    }
                    if(content[j].branch.bra == "辰"){
                        content[j].B = "朋友";
                    }
                }
            }else {
                for(int j =0; j < 6 ; j++){
                    if(content[j].branch.bra == "未"){
                        content[j].B = "朋友";
                    }
                    if(content[j].branch.bra == "丑"){
                        if(sex)
                            content[j].B = "妻子";
                        else
                            content[j].B ="小人";
                    }
                }
            }
        }
        else if(center.sec == "火"){
                if(hour % 2 == 0){
                    for(int j =0; j < 6 ; j++){
                        if(content[j].branch.bra == "辰"){
                            content[j].B ="疾病";
                        }
                        if(content[j].branch.bra == "戌"){
                            content[j].B = "朋友";
                        }
                    }
                }else {
                    for(int j =0; j < 6 ; j++){
                        if(content[j].branch.bra == "未"){
                            content[j].B = "朋友";
                        }
                        if(content[j].branch.bra == "丑"){
                            content[j].B ="疾病";
                        }
                    }
                }
        }
        else {
            for (i = 0; i < 5; i++) {
                if (s[i].equals(center))
                    break;
            }
            for (int j = 0; j < 6; j++) {
                if (content[i].branch.bra != center.branch && s[i].branch.contains(content[i].branch.bra)) {
                    content[i].B = "朋友";
                }
            }
        }
        int[][] display = new int[][]{
                {R.id.animal0, R.id.start0, R.id.secret0, R.id.task0_1, R.id.task0_2, R.id.task0_3},
                {R.id.animal1, R.id.start1, R.id.secret1, R.id.task1_1, R.id.task1_2, R.id.task1_3},
                {R.id.animal2, R.id.start2, R.id.secret2, R.id.task2_1, R.id.task2_2, R.id.task2_3},
                {R.id.animal3, R.id.start3, R.id.secret3, R.id.task3_1, R.id.task3_2, R.id.task3_3},
                {R.id.animal4, R.id.start4, R.id.secret4, R.id.task4_1, R.id.task4_2, R.id.task4_3},
                {R.id.animal5, R.id.start5, R.id.secret5, R.id.task5_1, R.id.task5_2, R.id.task5_3},
        };
        EditText animal;
        EditText star;
        EditText secret;
        EditText task1;
        EditText task2;
        EditText task3;
        for(i = 0; i < 6; i++){
                animal = (EditText) findViewById(display[i][0]);
                animal.setText(content[i].animal);

                star =(EditText) findViewById(display[i][1]);
                star.setText(content[i].star);

                secret = (EditText) findViewById(display[i][2]);
                secret.setText(content[i].branch.bra.toString());

                task1 = (EditText) findViewById(display[i][3]);
                task1.setText(content[i].A);

                task2 = (EditText) findViewById(display[i][4]);
                task2.setText(content[i].B);

                task3 = (EditText) findViewById(display[i][5]);
                task3.setText(content[i].C);
        }
/*
        for(i =0; i < 6; i++){
            for(int j = 0; j < 6; j++){
               // EditText ed = (EditText) findViewById(R.id.animal0);
              //  ed.setText("hahha");
            }
        }

 */


    }

}