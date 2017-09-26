package bw.com.day8_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
String [] texts={"少妇","萝莉","御姐","熟女","人妻"};
    //图片资源
    int[] images={R.mipmap.x,R.mipmap.g,R.mipmap.h,R.mipmap.z,R.mipmap.zz};
    private List<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());
    }

    private void initData() {
        list = new ArrayList<>();
        int index1=0;
        int index2=0;
        int index3=0;
        for (int i = 0; i <10; i++) {
            Data data=new Data();
            if(i%3==0){
                data.setText(texts[index1%texts.length]);
                data.setType(0);
                //为了防止脚标越界，还有索引有顺序的增加
                index1=(index1+1)%texts.length;
            }else if(i%2==0){
                //是一个textView和一个imageView
                data.setText(texts[index2]);
                data.setImages(images[index2]);
                data.setType(1);
                index2=(index2+1)%texts.length;
            }else{
                //上面一个textView和下面一个imageView
                data.setText(texts[index3]);
                data.setImages(images[index3]);
                data.setType(2);
                index3=(index3+1)%texts.length;
            }
            list.add(data);
        }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }
        /**
         * 每个item的类型是什么
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            return list.get(position).getType();
        }
        /**
         * item的类型的个数
         * @return
         */
        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder1 holder1=null;
            ViewHolder2 holder2=null;
            ViewHolder3 holder3=null;
            //得到不同item的类型
            int type = getItemViewType(position);
            if(convertView==null){
              switch (type){
                  case 0:
                      holder1=new ViewHolder1();
                      convertView= View.inflate(MainActivity.this, R.layout.item, null);

                      holder1.tv1 = (TextView) convertView.findViewById(R.id.tv1);
                      convertView.setTag(holder1);
                      break;
                  case 1:
                      holder2=new ViewHolder2();
                      convertView= View.inflate(MainActivity.this, R.layout.item2, null);
                      holder2.tv2= (TextView) convertView.findViewById(R.id.tv2);
                      holder2.iv2 = (ImageView) convertView.findViewById(R.id.iv2);
                      convertView.setTag(holder2);
                      break;
                  case 2:
                      holder3=new ViewHolder3();
                      convertView= View.inflate(MainActivity.this, R.layout.item3, null);
                      holder3.tv3= (TextView) convertView.findViewById(R.id.tv3);
                      holder3.iv3 = (ImageView) convertView.findViewById(R.id.iv3);
                      convertView.setTag(holder3);
                      break;
              }
            }else {
                //不为空,取出
                switch (type){
                    case 0:
                        holder1= (ViewHolder1) convertView.getTag();
                        break;
                    case 1:
                        holder2= (ViewHolder2) convertView.getTag();
                        break;
                    case 2:
                        holder3= (ViewHolder3) convertView.getTag();
                        break;
                }
            }
            switch (type){
                case 0:
                    holder1.tv1.setText(list.get(position).getText());
                    break;
                case 1:
                    holder2.tv2.setText(list.get(position).getText());
                    holder2.iv2.setImageResource(list.get(position).getImages());
                    break;
                case 2:
                    holder3.tv3.setText(list.get(position).getText());
                    holder3.iv3.setImageResource(list.get(position).getImages());
                    break;
            }
            return convertView;
        }
    }
    class ViewHolder1{
        TextView tv1;
    }
    class ViewHolder2{
        TextView tv2;
        ImageView iv2;
    }
    class ViewHolder3{
        TextView tv3;
        ImageView iv3;
    }
}
