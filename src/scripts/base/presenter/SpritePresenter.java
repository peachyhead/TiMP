package scripts.base.presenter;

import com.sun.javafx.geom.Vec2f;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import scripts.base.disposable.IDisposable;

public abstract class SpritePresenter{

    private Pane layer;

    private Image image;
    private ImageView imageView;

    private Vec2f position;

    double w;
    double h;


    public SpritePresenter(Pane layer, Image image, Vec2f position) {

        this.layer = layer;
        this.image = image;
        this.position = position;

        this.imageView = new ImageView(image);
        this.imageView.relocate(position.x, position.y);

        this.w = image.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = image.getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();
    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    //region Getters and Setters

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public Vec2f getPosition() {
        return position;
    }

    public void setPosition(Vec2f value) {
        position = value;
    }

    public ImageView getView() {
        return imageView;
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getCenterX() {
        return position.x + w * 0.5;
    }

    public double getCenterY() {
        return position.y + h * 0.5;
    }

    //endregion
}
