package com.example.doan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.doan.CenterActivity;
import com.example.doan.LoginActivity;
import com.example.doan.QuestionData;
import com.example.doan.R;
import com.example.doan.ResultActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LamTestFragment extends Fragment {
    public static final String TAG=LamTestFragment.class.getName();
    private View mView;
    private TextView question,indicator;
    private LinearLayout container;
    private Button btnNext,btnThoat,btnResult;
    private RatingBar ratingBar;
    private ProgressBar progressBar;
    private int myRating;
    private CenterActivity mCenterActivity;
    public static ArrayList<QuestionData> list;
    QuestionData questionData;
    public static int position=0;
    public static int score=0;
    public static int now=0;
    public static int score_1_10=0;
    public static int score_11_20=0;
    public static int score_21_30=0;
    public static int score_31_40=0;
    public static int score_41_50=0;
    public static int score_51_60=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_lamtest,container,false);
        initUI();
        mCenterActivity=(CenterActivity) getActivity();
        list=new ArrayList<>();
        list.add(new QuestionData("Hảy suy nghĩ thật kĩ trước khi chọn."));
        list.add(new QuestionData("1. Tôi có thể sử dụng/ vận hành/ bảo trì máy móc, thiết bị."));
        list.add(new QuestionData("2. Tôi thích làm việc ngoài trời hơn ngồi làm trong văn phòng."));
        list.add(new QuestionData("3. Tôi là người suy nghĩ thực tế."));
        list.add(new QuestionData("4. Tôi thích làm vườn, trồng cây xanh, hoa kiểng."));
        list.add(new QuestionData("5. Tôi thích làm những công việc thấy ngay kết quả."));
        list.add(new QuestionData("6. Tôi là người thích sự cụ thể, rõ ràng, thẳng thắn."));
        list.add(new QuestionData("7. Tôi thích tham dự các khóa học kĩ thuật (điện, sửa chửa, máy móc)."));
        list.add(new QuestionData("8. Tôi là người có tính tự lập."));
        list.add(new QuestionData("9. Tôi là người yêu thích vận động và sử dụng tay chân để làm việc."));
        list.add(new QuestionData("10. Tôi có thể đọc bản vẻ, bản thiết kế."));
        list.add(new QuestionData("11. Tôi hay tìm hiểu nhiều ý kiến khác nhau về một vấn đề cụ thể nào đó."));
        list.add(new QuestionData("12. Tôi có thể tiếp thu nhanh các lý thuyết khoa học."));
        list.add(new QuestionData("13. Tôi biết suy nghĩ một cách logic, mạch lạc, chặt chẻ."));
        list.add(new QuestionData("14. Tôi thích thực hiện các thí nghiệm hay nghiên cứu."));
        list.add(new QuestionData("15. Tôi có khả năng tổng hợp, khái quát phân tích, và giải quyết các vấn đề."));
        list.add(new QuestionData("16. Tôi thích thiết lập các đề tài nghiên cứu, làm khảo sát và kiểm tra kết quả."));
        list.add(new QuestionData("17. Tôi thích làm việc một mình."));
        list.add(new QuestionData("18. Tôi có thể giải các bài toán khó phức tạp."));
        list.add(new QuestionData("19. Tôi thích thảo luận nghiên cứu nguồn gốc các loài động thực vật."));
        list.add(new QuestionData("20. Tôi có sự hiểu biết rộng rãi về đời sống."));
        list.add(new QuestionData("21. Tôi là người nhạy cảm, lãng mạn, dể xúc động."));
        list.add(new QuestionData("22. Tôi là người giàu trí tưởng tượng."));
        list.add(new QuestionData("23. Tôi thích sự tự do, không theo những quy địng, quy tắc."));
        list.add(new QuestionData("24. Tôi có khả năng thuyết trình, diển xuất."));
        list.add(new QuestionData("25. Tôi có thể chụp ảnh, vẽ tranh, trang trí, điêu khắc."));
        list.add(new QuestionData("26. Tôi có năng khiếu âm nhạc."));
        list.add(new QuestionData("27. Tôi có khả năng sáng tác nhạc, viết kịch, làm thơ."));
        list.add(new QuestionData("28. Tôi thích sự sáng tạo đổi mới."));
        list.add(new QuestionData("29. Tôi thoải mái bộc lộ những ý thích."));
        list.add(new QuestionData("30. Tôi thích tham gia các khóa học thiết kế, năng khiếu nghệ thuật."));
        list.add(new QuestionData("31. Tôi là người thân thiện, hay giúp đở người khác."));
        list.add(new QuestionData("32. Tôi thích gặp gỡ làm việc với con người."));
        list.add(new QuestionData("33. Tôi có thể hòa giải các mâu thuẩn, tranh chấp."));
        list.add(new QuestionData("34. Tôi thích khuyên bảo, huấn luyện hay giảng giải cho người khác."));
        list.add(new QuestionData("35. Tôi thích tham gia các hội thảo về phát triển cộng đồng và giải quyết các vấn đề xã hội."));
        list.add(new QuestionData("36. Tôi rất hay giúp đỡ người khác."));
        list.add(new QuestionData("37. Tôi thích tham gia các hoạt động tình nguyện với các đội nhóm tại trường, phường, nhóm, hay cộng đồng."));
        list.add(new QuestionData("38. Tôi là người có tinh thần đồng đội,tinh thần hợp tác."));
        list.add(new QuestionData("39. Tôi thích phát biểu, đóng góp ý kiến trong các cuộc thảo luận."));
        list.add(new QuestionData("40. Tôi có thể diển đạt suy nghĩ và cảm xúc của mình rất rõ ràng."));
        list.add(new QuestionData("41. Tôi thích phiêu lưu mạo hiểm."));
        list.add(new QuestionData("42. Tôi thích có quyền lực, địa vị, thích được bầu cử vào những vị trí quan trọng."));
        list.add(new QuestionData("43. Tôi là người năng động.."));
        list.add(new QuestionData("44. Tôi có khả năng diễn đạt, tranh luận và thuyết phục người khác."));
        list.add(new QuestionData("45. Tôi là người có nhiều hoài bảo tham vọng."));
        list.add(new QuestionData("46. Tôi thường lên kế hoạch/ chiến lược để đạt được mục tiêu của mình."));
        list.add(new QuestionData("47. Tôi thích gây ảnh hưởng đến người khác."));
        list.add(new QuestionData("48. Tôi là người thích cạnh tranh và muốn mình phải giỏi hơn người khác."));
        list.add(new QuestionData("49. Tôi thích tham gia các khóa học về kinh doanh, marketing, bán hàng."));
        list.add(new QuestionData("50. Tôi có tính quyết đoán."));
        list.add(new QuestionData("51. Tôi thích lập thời khóa biểu, sắp xếp lịch làm việc."));
        list.add(new QuestionData("52. Tôi làm việc tốt trong khuôn khổ và hệ thống."));
        list.add(new QuestionData("53. Tôi là người chu đáo, chính xác và đáng tin cậy."));
        list.add(new QuestionData("54. Tôi thích công việc tính toán sổ sách, ghi chép số kiệu, văn bản."));
        list.add(new QuestionData("55. Tôi có thể giải quyết các công việc giấy tờ một cách nhanh chóng, hiệu quả, ngăn nắp."));
        list.add(new QuestionData("56. Tôi thường đặt ra những mục tiêu, kế hoạch trong cuộc sống."));
        list.add(new QuestionData("57. Tôi thích dự kiến các khoản thu chi."));
        list.add(new QuestionData("58. Tôi có thể tổ chức dàn dựng các chương trình cho các hoạt động, sự kiện (như tổ chức buổi dã ngoại cho lớp, tổ chức đêm ca....)."));
        list.add(new QuestionData("59. Tôi là người làm việc có nguyên tắc, có trình tự, có kế hoạch."));
        list.add(new QuestionData("60. Tôi thích học, tìm hiểu các thủ tục, quy định, luật lệ (vd: luật thuế, luật kinh doanh)."));
        list.add(new QuestionData("Chúc mừng bạn đã hoàn thành xong bài kiểm tra, nhấn tiếp để xem kết quả."));
        questionData=list.get(position);
        setQuestionDataData();
        initListener();
        return mView;
    }



    private void initListener() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentProgress=progressBar.getProgress();
                position=position+1;
                questionData=list.get(position);
                setQuestionDataData();
                progressBar.setProgress(currentProgress+10);
                indicator.setText(position+"/60");
                ratingBar.setRating(0);
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        int ratingscore=(int) rating;
                        myRating= (int)ratingBar.getRating();
                        switch (ratingscore){
                            case 1: score=0;
                                break;
                            case 2: score=4;
                                break;
                            case 3: score=6;
                                break;
                            case 4: score=8;
                                break;
                            case 5: score=10;
                                break;
                        }
                    }
                });
                score_1_10 = score_1_10 +score;
                if(position==11){
                    now=score_1_10;
                    Toast.makeText(getActivity(), "Diem tu 10 cau dau la :"+ now, Toast.LENGTH_LONG).show();
                }
                if(position==21){
                    score_11_20=score_1_10-now;
                    Toast.makeText(getActivity(), "Diem tu 11 den cau 20 la :"+score_11_20, Toast.LENGTH_SHORT).show();
                }
                if(position==31){
                    score_21_30=score_1_10-(now+score_11_20);
                    Toast.makeText(getActivity(), "Diem tu 21 den cau 30 la :"+score_21_30, Toast.LENGTH_SHORT).show();
                }
                if(position==41){
                    score_31_40=score_1_10-(now+score_11_20+score_21_30);
                    Toast.makeText(getActivity(), "Diem tu 31 den cau 40 la :"+score_31_40, Toast.LENGTH_SHORT).show();
                }
                if(position==51){
                    score_41_50=score_1_10-(now+score_11_20+score_21_30+score_31_40);
                    Toast.makeText(getActivity(), "Diem tu 41 den cau 50 la :"+score_41_50, Toast.LENGTH_SHORT).show();
                }
                if(position==61){
                    score_51_60=score_1_10-(now+score_11_20+score_21_30+score_31_40+score_41_50);
                    Toast.makeText(getActivity(), "Diem tu 51 den cau 60 la :"+score_51_60, Toast.LENGTH_SHORT).show();
                    btnResult.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);
                }

            }
        });
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("d_1_10",now);
                intent.putExtra("d_11_20",score_11_20);
                intent.putExtra("d_21_30",score_21_30);
                intent.putExtra("d_31_40",score_31_40);
                intent.putExtra("d_41_50",score_41_50);
                intent.putExtra("d_51_60",score_51_60);
                startActivity(intent);

            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=0;
                score_1_10=0;
                score_11_20=0;
                score_21_30=0;
                score_31_40=0;
                score_41_50=0;
                score_51_60=0;
                mCenterActivity.goToFragmentHuongdanHolland();
            }
        });
    }


    private void setQuestionDataData() {
        question.setText(questionData.getQuestion());
    }
    private void initUI() {
        question=mView.findViewById(R.id.question);
        indicator=mView.findViewById(R.id.indicator);
        container=mView.findViewById(R.id.linearLayout2);
        btnNext=mView.findViewById(R.id.btn_next);
        ratingBar=mView.findViewById(R.id.ratingBar);
        progressBar=mView.findViewById(R.id.progressTiendo);
        btnThoat=mView.findViewById(R.id.thoatKT);
        btnResult=mView.findViewById(R.id.btn_Result);
    }
}
