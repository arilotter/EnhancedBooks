package enhancedbooks.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

// Time Created: 3/8/13 10:56 AM

public class ModelStorageShelf extends ModelBase {

    //Woo.
    ModelRenderer Back;
    ModelRenderer Left;
    ModelRenderer Right;
    ModelRenderer Bottom;
    ModelRenderer Top;
    ModelRenderer Shelf;
    ModelRenderer Book1_1;
    ModelRenderer Book1_2;
    ModelRenderer Book2_1;
    ModelRenderer Book2_2;
    ModelRenderer Book3;
    ModelRenderer Book4_1;
    ModelRenderer Book4_2;
    ModelRenderer Book5;
    ModelRenderer Book6_1;
    ModelRenderer Book6_2;
    ModelRenderer Book7_1;
    ModelRenderer Book7_2;
    ModelRenderer Book8_1;
    ModelRenderer Book8_2;
    ModelRenderer Book8_3;

    public ModelStorageShelf() {
        textureWidth = 128;
        textureHeight = 64;

        //.setTextureSize(textureWidth, textureHeight);
        Back = new ModelRenderer(this, 0, 17).setTextureSize(textureWidth, textureHeight);
        Back.addBox(0F, 1F, 0F, 16, 14, 1);

        Bottom = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight);
        Bottom.addBox(0F, 0F, 0F, 16, 16, 1);
        Bottom.setRotationPoint(0F, 16F, 0F);
        Bottom.rotateAngleX = 1.570796F;

        Right = new ModelRenderer(this, 0, 32).setTextureSize(textureWidth, textureHeight);
        Right.addBox(0F, 0F, 0F, 15, 14, 1);
        Right.setRotationPoint(16F, 1F, 1F);
        Right.rotateAngleY = -1.570796F;

        Shelf = new ModelRenderer(this, 0, 47).setTextureSize(textureWidth, textureHeight);
        Shelf.addBox(0F, 0F, 0F, 14, 15, 2);
        Shelf.setRotationPoint(1F, 9F, 1F);
        Shelf.rotateAngleX = 1.570796F;

        Left = new ModelRenderer(this, 0, 32).setTextureSize(textureWidth, textureHeight);
        Left.addBox(0F, 0F, 0F, 15, 14, 1);
        Left.setRotationPoint(1F, 1F, 1F);
        Left.rotateAngleY = -1.570796F;

        Top = new ModelRenderer(this, 0, 0).setTextureSize(textureWidth, textureHeight);
        Top.addBox(0F, 0F, 0F, 16, 16, 1);
        Top.setRotationPoint(0F, 1F, 0F);
        Top.rotateAngleX = 1.570796F;

        //BOOKS YAAAAY T_T
        Book1_1 = new ModelRenderer(this, 34, 0).setTextureSize(textureWidth, textureHeight);
        Book1_2 = new ModelRenderer(this, 48, 0).setTextureSize(textureWidth, textureHeight);
        Book1_1.addBox(1F, 9F, 10F, 2, 5, 5);
        Book1_2.addBox(3F, 9F, 10F, 1, 4, 5);

        Book2_1 = new ModelRenderer(this, 60, 0).setTextureSize(textureWidth, textureHeight);
        Book2_2 = new ModelRenderer(this, 74, 0).setTextureSize(textureWidth, textureHeight);
        Book2_1.addBox(4F, 9F, 10F, 2, 5, 5);
        Book2_2.addBox(6F, 9F, 10F, 2, 6, 5);

        Book3 = new ModelRenderer(this, 88, 0).setTextureSize(textureWidth, textureHeight);
        Book3.addBox(10.5F, 7.4F, 10F, 2, 5, 5);
        Book3.setRotationPoint(0F, 0F, 0F);
        Book3.rotateAngleZ = 0.1745329F;


        Book4_1 = new ModelRenderer(this, 102, 0).setTextureSize(textureWidth, textureHeight);
        Book4_2 = new ModelRenderer(this, 34, 11).setTextureSize(textureWidth, textureHeight);
        Book4_1.addBox(11F, 9F, 10F, 2, 5, 5);
        Book4_2.addBox(13F, 9F, 10F, 2, 4, 5);


        //Row2
        Book5 = new ModelRenderer(this, 48, 11).setTextureSize(textureWidth, textureHeight);
        Book5.addBox(1F, 1F, 10F, 3, 4, 5);

        Book6_1 = new ModelRenderer(this, 64, 11).setTextureSize(textureWidth, textureHeight);
        Book6_2 = new ModelRenderer(this, 78, 11).setTextureSize(textureWidth, textureHeight);
        Book6_1.addBox(4F, 1F, 10F, 2, 5, 5);
        Book6_2.addBox(6F, 1F, 10F, 1, 3, 5);

        Book7_1 = new ModelRenderer(this, 90, 11).setTextureSize(textureWidth, textureHeight);
        Book7_2 = new ModelRenderer(this, 102, 11).setTextureSize(textureWidth, textureHeight);
        Book7_1.addBox(7F, 2F, 10F, 1, 5, 5);
        Book7_1.setRotationPoint(0F, 0F, 0.0F);
        Book7_1.rotateAngleZ = -0.1745329F;
        Book7_2.addBox(9F, 1F, 10F, 2, 5, 5);

        Book8_1 = new ModelRenderer(this, 34, 22).setTextureSize(textureWidth, textureHeight);
        Book8_2 = new ModelRenderer(this, 46, 22).setTextureSize(textureWidth, textureHeight);
        Book8_3 = new ModelRenderer(this, 60, 22).setTextureSize(textureWidth, textureHeight);
        Book8_1.addBox(11F, 1F, 10F, 1, 4, 5);
        Book8_2.addBox(12F, 1F, 10F, 2, 6, 5);
        Book8_3.addBox(14F, 1F, 10F, 1, 4, 5);

    }

    public void render(float scale, boolean[] filledSlots) {
        Back.render(scale);
        Top.render(scale);
        Bottom.render(scale);
        Right.render(scale);
        Left.render(scale);
        Shelf.render(scale);

        if (filledSlots[7]) {
            Book8_1.render(scale);
            Book8_2.render(scale);
            Book8_3.render(scale);
        }
        if (filledSlots[6]) {
            Book7_1.render(scale);
            Book7_2.render(scale);
        }
        if (filledSlots[5]) {
            Book6_1.render(scale);
            Book6_2.render(scale);
        }
        if (filledSlots[4]) {
            Book5.render(scale);
        }
        if (filledSlots[3]) {
            Book4_1.render(scale);
            Book4_2.render(scale);
        }
        if (filledSlots[2]) {
            Book3.render(scale);
        }
        if (filledSlots[1]) {
            Book2_1.render(scale);
            Book2_2.render(scale);
        }
        if (filledSlots[0]) {
            Book1_1.render(scale);
            Book1_2.render(scale);
        }
    }
}
