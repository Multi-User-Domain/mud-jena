/**
 * 
 */
package com.mackervoy.calum.mud;

/**
 * @author Calum Mackervoy
 * An abstract class for any class which manages the returning of File system/URI locations for some path
 */
public abstract class AbstractFileStorageWrapper {
	protected String path;
	
	private String cleanPath(String subPath) {
		String root = MUDApplication.getRootDirectory();
		
		// normalise style
		if(subPath.startsWith("./")) subPath = subPath.substring(2);
		if(root.startsWith("./")) root = root.substring(2);
		
		// enforce relativity
		if(subPath.startsWith(root)) subPath = subPath.substring(root.length());
		
		// enforce trailing /
		if(subPath.length() > 0 && !subPath.endsWith("/")) return subPath + "/";
		return subPath;
	}
	
	public AbstractFileStorageWrapper(String path) {
		this.path = this.cleanPath(path);
	}
	
	public String getUri() {
		return MUDApplication.getSiteUrl() + this.path;
	}
	
	public String getFileLocation() {
		return MUDApplication.getRootDirectory() + this.path;
	}
	
	/**
	 * @param shortHand a short string to help human readers understand the type of the resource
	 * @param name identifier for the resource
	 * @return a full URI with the dataset path + the shorthand type + a random ID
	 */
	public String getNewResourceUri(String shortHand, String name) {
		return this.getUri() + "#" + shortHand + "-" + name;
	}
	
	public String getNewResourceUri(String shortHand) {
		return this.getNewResourceUri(shortHand, Random.getRandomUUIDString());
	}
}
