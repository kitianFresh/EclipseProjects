package test;

import java.util.List;

public interface VirtualFile {
	boolean isDir();
	long size();
	String name();
	List<VirtualFile> children();
}
