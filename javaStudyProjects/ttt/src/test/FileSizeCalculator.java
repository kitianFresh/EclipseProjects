package test;

import java.util.List;


public class FileSizeCalculator {
    private long sumSize = 0;
    public FileSizeCalculator(){
    }
	public long sumSizeOf(VirtualFile file){
        if(!file.isDir()) return file.size();
        List<VirtualFile> lists = file.children();
        VirtualFile vfile = null;
        for(int i=0;i<lists.size();i++){
            vfile = lists.get(i);
            sumSize += sumSizeOf(vfile);
        }
        return sumSize;
	}
}
