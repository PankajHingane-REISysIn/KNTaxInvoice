/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gen.dto;

import java.awt.Font;

/**
 *
 * @author pc5
 */
public class ScreenDTO {

    private Integer screenWidth;
    private Integer screenHeight;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private Integer flowLblLocationX;
    private Integer flowLblLocationY;
    private Integer fontValue = 12;
    private Font font = new Font("Tahoma", Font.BOLD, fontValue);

    /**
     * @return the screenWidth
     */
    public Integer getScreenWidth() {
        return screenWidth;
    }

    /**
     * @param screenWidth the screenWidth to set
     */
    public void setScreenWidth(Integer screenWidth) {
        this.screenWidth = screenWidth;
    }

    /**
     * @return the screenHeight
     */
    public Integer getScreenHeight() {
        return screenHeight;
    }

    /**
     * @param screenHeight the screenHeight to set
     */
    public void setScreenHeight(Integer screenHeight) {
        this.screenHeight = screenHeight;
    }

    /**
     * @return the xCoordinate
     */
    public Integer getxCoordinate() {
        return xCoordinate;
    }

    /**
     * @param xCoordinate the xCoordinate to set
     */
    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * @return the yCoordinate
     */
    public Integer getyCoordinate() {
        return yCoordinate;
    }

    /**
     * @param yCoordinate the yCoordinate to set
     */
    public void setyCoordinate(Integer yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * @return the flowLblLocationX
     */
    public Integer getFlowLblLocationX() {
        return flowLblLocationX;
    }

    /**
     * @param flowLblLocationX the flowLblLocationX to set
     */
    public void setFlowLblLocationX(Integer flowLblLocationX) {
        this.flowLblLocationX = flowLblLocationX;
    }

    /**
     * @return the flowLblLocationY
     */
    public Integer getFlowLblLocationY() {
        return flowLblLocationY;
    }

    /**
     * @param flowLblLocationY the flowLblLocationY to set
     */
    public void setFlowLblLocationY(Integer flowLblLocationY) {
        this.flowLblLocationY = flowLblLocationY;
    }

    /**
     * @return the fontValue
     */
    public Integer getFontValue() {
        return fontValue;
    }

    /**
     * @param fontValue the fontValue to set
     */
    public void setFontValue(Integer fontValue) {
        this.fontValue = fontValue;
    }

    /**
     * @return the font
     */
    public Font getFont() {
        return font;
    }

    /**
     * @param font the font to set
     */
    public void setFont(Font font) {
        this.font = font;
    }
}
