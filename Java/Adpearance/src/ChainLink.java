enum Side { NONE, LEFT, RIGHT }

public class ChainLink {
    private ChainLink left, right;

    public void append(ChainLink rightPart) {
        if (this.right != null)
            throw new IllegalStateException("Link is already connected.");

        this.right = rightPart;
        rightPart.left = this;
    }

    public Side longerSide() {

        Side longerSide = Side.NONE;

        // no left or right, only one link
        if (this.left == null && this.right == null) {
            longerSide = Side.NONE;

        // middle, left and right side exist
        } else if (this.left != null && this.right != null) {
            longerSide = Side.NONE;

        // middle, looped links, must be checked after above != null conditions
        } else if (this.left == this.right) {
            longerSide = Side.NONE;

        // no left side, must be longer on the right
        } else if (this.left == null && this.right != null) {
            longerSide = Side.RIGHT;

        // no right side, must be longer on the left
        } else if (this.right == null && this.left != null) {
            longerSide = Side.LEFT;
        }

        return longerSide;
    }

    public static void main(String[] args) {
        ChainLink left = new ChainLink();
        ChainLink middle = new ChainLink();
        ChainLink right = new ChainLink();
        left.append(middle);
        middle.append(right);

        System.out.println(left.longerSide());
    }
}