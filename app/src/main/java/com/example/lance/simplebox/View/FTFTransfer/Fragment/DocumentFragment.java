package com.example.lance.simplebox.View.FTFTransfer.Fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lance.simplebox.Adapter.FTFFileAdapter;
import com.example.lance.simplebox.R;
import com.example.lance.simplebox.View.FTFTransfer.Utils.ScanOfficeFileUtil;
import static com.example.lance.simplebox.View.FTFTransfer.FTFContent.FTFContent.*;


/**
 * Created by Lance on 2017/11/21.
 */

public class DocumentFragment extends Fragment implements View.OnClickListener{

    private View view;

    private RecyclerView wordRecycler;
    private RecyclerView pptRecycler;
    private RecyclerView excelRecycler;
    private RecyclerView pdfRecycler;

    private LinearLayout wordLayout;
    private LinearLayout pptLayout;
    private LinearLayout excelLayout;
    private LinearLayout pdfLayout;

    private ImageView wordArrow;
    private ImageView pptArrow;
    private ImageView excelArrow;
    private ImageView pdfArrow;

    private boolean isWordOpen;
    private boolean isPPTOpen;
    private boolean isExcelOpen;
    private boolean isPDFOpen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_document,container,false);
        //初始化数据;
        initData();
        //初始化控件;
        initWight(view);
        return view;
    }

    private void initData() {
        /**
         * 初始化数据部分;
         * */
        isWordOpen = false;
        isPPTOpen = false;
        isExcelOpen = false;
        isPDFOpen = false;

    }

    private void initWight(View view) {
        wordLayout = (LinearLayout) view.findViewById(R.id.ftf_word);
        pptLayout = (LinearLayout) view.findViewById(R.id.ftf_ppt);
        excelLayout = (LinearLayout) view.findViewById(R.id.ftf_excel);
        pdfLayout = (LinearLayout) view.findViewById(R.id.ftf_pdf);

        wordArrow = (ImageView) view.findViewById(R.id.ftf_word_arrow);
        pptArrow = (ImageView) view.findViewById(R.id.ftf_ppt_arrow);
        excelArrow = (ImageView) view.findViewById(R.id.ftf_excel_arrow);
        pdfArrow = (ImageView) view.findViewById(R.id.ftf_pdf_arrow);

        wordRecycler = (RecyclerView) view.findViewById(R.id.ftf_word_recycle);
        pptRecycler = (RecyclerView) view.findViewById(R.id.ftf_ppt_recycle);
        excelRecycler = (RecyclerView) view.findViewById(R.id.ftf_excel_recycle);
        pdfRecycler = (RecyclerView) view.findViewById(R.id.ftf_pdf_recycle);

        /**
         * 初始化列表是否展开;
         * */
        if(isWordOpen){
            openDiretory(wordArrow);
        }else{
            closeDiretory(wordArrow);
        }

        if (isPPTOpen){
            openDiretory(pptArrow);
        }else{
            closeDiretory(pptArrow);
        }

        if (isExcelOpen){
            openDiretory(excelArrow);
        }else{
            closeDiretory(excelArrow);
        }

        if (isPDFOpen){
            openDiretory(pdfArrow);
        }else{
            closeDiretory(pdfArrow);
        }

        /**
         * 列表的点击事件;
         * */
        wordLayout.setOnClickListener(this);
        pptLayout.setOnClickListener(this);
        excelLayout.setOnClickListener(this);
        pdfLayout.setOnClickListener(this);

        wordRecycler.setVisibility(View.GONE);
        pptRecycler.setVisibility(View.GONE);
        excelRecycler.setVisibility(View.GONE);
        pdfRecycler.setVisibility(View.GONE);

        /**
         * 实例化各列表的RecycleView;
         * */
        LinearLayoutManager wordLM = new LinearLayoutManager(getContext());
        wordLM.setOrientation(LinearLayoutManager.VERTICAL);
        wordRecycler.setLayoutManager(wordLM);
        FTFFileAdapter wordAdapter = new FTFFileAdapter(getContext(),wordList,R.mipmap.ftf_word);
        wordRecycler.setAdapter(wordAdapter);

        LinearLayoutManager pptLM = new LinearLayoutManager(getContext());
        pptLM.setOrientation(LinearLayoutManager.VERTICAL);
        pptRecycler.setLayoutManager(pptLM);
        FTFFileAdapter pptAdapter = new FTFFileAdapter(getContext(),pptList,R.mipmap.ftf_ppt);
        pptRecycler.setAdapter(pptAdapter);

        LinearLayoutManager excelLM = new LinearLayoutManager(getContext());
        excelLM.setOrientation(LinearLayoutManager.VERTICAL);
        excelRecycler.setLayoutManager(excelLM);
        FTFFileAdapter excelAdapter = new FTFFileAdapter(getContext(),excelList,R.mipmap.ftf_excel);
        excelRecycler.setAdapter(excelAdapter);

        LinearLayoutManager pdfLM = new LinearLayoutManager(getContext());
        pdfLM.setOrientation(LinearLayoutManager.VERTICAL);
        pdfRecycler.setLayoutManager(pdfLM);
        FTFFileAdapter pdfAdapter = new FTFFileAdapter(getContext(),pdfList,R.mipmap.ftf_pdf);
        pdfRecycler.setAdapter(pdfAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ftf_word:
                if(isWordOpen){
                    wordRecycler.setVisibility(View.GONE);
                    closeDiretory(wordArrow);
                    isWordOpen = false;
                }else{
                    wordRecycler.setVisibility(View.VISIBLE);
                    openDiretory(wordArrow);
                    isWordOpen = true;
                }
                break;
            case R.id.ftf_ppt:
                if(isPPTOpen){
                    wordLayout.setVisibility(View.VISIBLE);

                    pptRecycler.setVisibility(View.GONE);
                    closeDiretory(pptArrow);
                    isPPTOpen = false;
                }else{
                    wordLayout.setVisibility(View.GONE);

                    pptRecycler.setVisibility(View.VISIBLE);
                    openDiretory(pptArrow);
                    isPPTOpen = true;
                }
                break;
            case R.id.ftf_excel:
                if(isExcelOpen){
                    wordLayout.setVisibility(View.VISIBLE);
                    pptLayout.setVisibility(View.VISIBLE);

                    excelRecycler.setVisibility(View.GONE);
                    closeDiretory(excelArrow);
                    isExcelOpen = false;
                }else{
                    wordLayout.setVisibility(View.GONE);
                    pptLayout.setVisibility(View.GONE);

                    excelRecycler.setVisibility(View.VISIBLE);
                    openDiretory(excelArrow);
                    isExcelOpen = true;
                }
                break;
            case R.id.ftf_pdf:
                if(isPDFOpen){
                    wordLayout.setVisibility(View.VISIBLE);
                    pptLayout.setVisibility(View.VISIBLE);
                    excelLayout.setVisibility(View.VISIBLE);

                    pdfRecycler.setVisibility(View.GONE);
                    closeDiretory(pdfArrow);
                    isPDFOpen = false;
                }else{
                    wordLayout.setVisibility(View.GONE);
                    pptLayout.setVisibility(View.GONE);
                    excelLayout.setVisibility(View.GONE);

                    pdfRecycler.setVisibility(View.VISIBLE);
                    openDiretory(pdfArrow);
                    isPDFOpen = true;
                }
                break;
        }
    }

    /**
     * 打开文件夹，旋转三角形;
     * */
    private void openDiretory(View v){
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,"rotation",0,90);
        animator.setDuration(300);
        animator.start();
    }

    /**
     * 关闭文件夹，旋转三角形;
     * */
    private void closeDiretory(View v){
        ObjectAnimator animator = ObjectAnimator.ofFloat(v,"rotation",90,0);
        animator.setDuration(300);
        animator.start();
    }
}
