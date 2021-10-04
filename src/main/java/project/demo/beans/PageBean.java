package project.demo.beans;

import lombok.Data;

@Data
public class PageBean {
    //최소 페이지 번호
    private int min;

    //최대 페이지 번호
    private int max;

    //이전 버튼의 페이지 번호
    private int prevPage;

    //다음 버튼의 페이지 번호
    private int nextPage;

    //전체 페이지개수
    private int pageCnt;

    //현재 페이지 번호
    private int currentPage;

    /**
     * 
     * @param contentCnt: 전체 글 개수 
     * @param currentPage: 현재 글 번호
     * @param contentPageCnt: 페이지당 글의 개수
     * @param paginationCnt: 페이지 버튼의 개수
     */
    public PageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {

        //현재 페이지 번호
        this.currentPage = currentPage;

        //전체 페이지 개수 : 전체 글 개수 / 페이지당 글의 개수
        pageCnt = contentCnt / contentPageCnt;

        //나눴을때 나머지가 나올 수 있기 때문에
        if(contentCnt % contentPageCnt > 0){
            pageCnt ++;
        }

        min = ((currentPage - 1) / contentPageCnt) * contentPageCnt +1;
        max = min + paginationCnt -1;

        //만약 전체 페이지 개수가 53이라면 51 ~60이 나올 수 있기 때문에
        if(max > pageCnt ){
            max = pageCnt;
        }

        prevPage = min - 1;
        nextPage = max + 1;
        if(nextPage > pageCnt){
            nextPage = pageCnt;
        }
        
    }
}
