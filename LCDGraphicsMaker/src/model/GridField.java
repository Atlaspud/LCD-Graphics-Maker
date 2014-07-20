package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GridField {
	private ArrayList<Block> gridBlocks;
	private ArrayList<Block> blocks;
	private Dimension fieldSize;
	private int blockSize;
	private Dimension gridSize;
	private boolean hasChanged = false;
	
	public GridField(Dimension size) {
		fieldSize = size;
		gridBlocks = new ArrayList<Block>();
		blocks = new ArrayList<Block>();
		gridSize = new Dimension(84,48);
		blockSize = 8;
	}
	
	public void startNew() {
		hasChanged = true;
		createGrid();
		createNewBlocks();
	}
	
	public void loadBlocks(String data) {
		hasChanged = false;
		int binaryArray [] = {128, 64, 32, 16, 8, 4, 2, 1};
		createGrid();
		createNewBlocks();
		int index = 0;
		String dataArray [] = data.split(",");
		for (String byteString : dataArray) {
			index += 8;
			int number = Integer.parseInt(byteString.split("x")[1],16);
			int binaryIndex = 0;
			for (int i = index-1; i >= index - 8; i--) {
				if (number >= binaryArray[binaryIndex]) {
					number -= binaryArray[binaryIndex];
					Block block = blocks.get(i);
					block.select();
				}
				binaryIndex++;
			}
		}
	}
	
	private void createGrid() {
		gridBlocks.clear();
		double divideField = (fieldSize.width-blockSize*gridSize.width)/2;
		for (int i = 0; i < gridSize.height; i++) {
			for (int j = 0; j < gridSize.width; j++) {
				Block block = new Block(true);
				block.size.setSize(blockSize, blockSize);
				block.position.setLocation(divideField + blockSize * j, blockSize * i);
				block.colour = Color.BLACK;
				gridBlocks.add(block);
			}
		}
	}
	
	private void createNewBlocks() {
		blocks.clear();
		double divideField = (fieldSize.width-blockSize*gridSize.width)/2;
		for (int group = 0; group < 6; group++) {
			for (int i = 0; i < gridSize.width; i++) {
				for (int j = 0; j < 8; j++) {
					Block block = new Block(false);
					block.size.setSize(blockSize, blockSize);
					block.position.setLocation(divideField + blockSize * i, (blockSize*8) * group + blockSize * j);
					block.colour = Color.WHITE;
					blocks.add(block);
				}
			}
		}
	}
	
	public void draw(Graphics g) {
		
		for (Block block : blocks) {
			block.draw(g);
		}
		
		for (Block block : gridBlocks) {
			block.draw(g);
		}
	}
	
	public void update(int x, int y) {
		hasChanged = true;
		// Update Grid State
		for (Block block: blocks) {
			if (block.hit(x, y)) {
				break;
			}
		}
	}
	
	public void updateDrag(int x, int y) {
		hasChanged = true;
		// Update Grid State
		for (Block block: blocks) {
			if (block.hitDrag(x, y)) {
				break;
			}
		}
	}
	
	private String generateFile() {
		StringBuilder builder = new StringBuilder();
		int row = 0;
		for (int group = 0; group < 6; group++) {
			for (int i = 0; i < gridSize.width; i++) {
				int number = 0;
				for (int j = 0; j < 8; j++) {
					int currentIndex = group * (gridSize.width * 8) + j + i * 8;
					Block block = blocks.get(currentIndex);
					if (block.isSelected()) {
						number += (int) (Math.pow(2, j));
					}
				}
				
				row++;
				if (row == 16) {
					if (number >= 16) {
						builder.append("0x" + Integer.toHexString(number).toUpperCase() + ",\n");
					} else {
						builder.append("0x0" + Integer.toHexString(number).toUpperCase() + ",\n");
					}
					row = 0;
				} else {
					if (number >= 16) {
						builder.append("0x" + Integer.toHexString(number).toUpperCase() + ",");
					} else {
						builder.append("0x0" + Integer.toHexString(number).toUpperCase() + ",");
					}
				}
			}
		}
		return builder.toString();
	}
	
	public void save(String filename) {
		hasChanged = false;
		String text = generateFile();
		try {
			PrintWriter writer = new PrintWriter(filename);
			writer.print(text);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean gridHasChanged() {
		return hasChanged;
	}
}
